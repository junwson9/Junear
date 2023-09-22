package hasix.junear.billionaire.application;

import hasix.junear.billionaire.application.dto.TodayLifeQuotes;
import hasix.junear.billionaire.domain.BillionaireRepository;
import hasix.junear.billionaire.domain.LifeQuotesRepository;
import hasix.junear.billionaire.exception.LifeQuotesErrorCode;
import hasix.junear.common.exception.CommonErrorCode;
import hasix.junear.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BillionaireLifeQuotesSearchUseCase {
    // 억만장자 데이터 조회
    private final BillionaireRepository billionaireRepository;
    private final LifeQuotesRepository lifeQuotesRepository;
    private final String phraseKey = "today_phrase";

    public List<TodayLifeQuotes> search() {
        List<TodayLifeQuotes> todayLifeQuotesList = billionaireRepository.findLifeQuotes(phraseKey);

        if (todayLifeQuotesList.size() == 0) {
            updateLifeQuotes(phraseKey);
            log.info("데이터 조회 재시도");
            todayLifeQuotesList = billionaireRepository.findLifeQuotes(phraseKey);
        }

        return todayLifeQuotesList;
    }

    private void updateLifeQuotes(String key) {
        List<TodayLifeQuotes> todayLifeQuotesList = billionaireRepository.findLifeQuotesByIds(getRandomIndex(3));
        if (todayLifeQuotesList.size() == 0) {
            throw new CustomException(LifeQuotesErrorCode.NOT_FOUND_TODAY_LIFE_QUOTES);
        }
        billionaireRepository.saveLifeQuotesList(key, getTTL(6, 0, LocalDateTime.now()), todayLifeQuotesList);
    }

    private List<Long> getRandomIndex(int number) {
        List<Long> randomIndexList = new ArrayList<>();
        Random random = new Random(); // 랜덤 객체 생성
        random.setSeed(System.currentTimeMillis());

        int billionaireCount = Math.toIntExact(getBillionaireCount());
        log.info("billionaire count : {}", billionaireCount);
        long[] randomIndexArray = new long[number];

        for (int i = 0; i < number; i++) {
            randomIndexArray[i] = random.nextInt(billionaireCount) + 1;
            for (int j = 0; j < i; j++) {
                if (randomIndexArray[j] == randomIndexArray[i]) {
                    i--;
                }
            }
        }

        for (int i = 0; i < number; i++) {
            int range = Math.toIntExact(getLifeQuotesCount(randomIndexArray[i]));
            if (range == 0) {
                throw new CustomException(LifeQuotesErrorCode.NOT_FOUND_LIFE_QUOTES);
            }
            log.info("life quotes range : {}", range);

            randomIndexList.add(((randomIndexArray[i] - 1) * 10L) + (random.nextInt(range) + 1));
        }
        log.info("random index : {}", randomIndexList);

        return randomIndexList;
    }

    private Long getBillionaireCount() {
        return billionaireRepository.countById();
    }

    private Long getLifeQuotesCount(Long id) {
        return lifeQuotesRepository.countByBillionaireId(id);
    }

    private long getTTL(int targetHour, int targetMin, LocalDateTime now) {
        LocalTime targetTime = LocalTime.of(targetHour, targetMin);
        LocalDateTime targetDateTime = now.with(targetTime);

        if (now.toLocalTime().isAfter(targetTime)) {
            // 이미 target 날짜를 지난 경우 다음 날 target 날짜로 설정
            targetDateTime = targetDateTime.plusDays(1);
        }

        long secondsUntilTargetTime = Duration.between(now, targetDateTime).getSeconds();

        return secondsUntilTargetTime;
    }

}
