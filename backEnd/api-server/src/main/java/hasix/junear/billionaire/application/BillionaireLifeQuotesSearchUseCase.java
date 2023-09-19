package hasix.junear.billionaire.application;

import hasix.junear.billionaire.application.dto.TodayLifeQuotes;
import hasix.junear.billionaire.domain.BillionaireRepository;
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
    private final String phraseKey = "today_phrase";

    public List<TodayLifeQuotes> search() {
        log.info("억만장자 데이터 조회 시작");
        List<TodayLifeQuotes> todayLifeQuotesList = billionaireRepository.findLifeQuotes(phraseKey);

        if (todayLifeQuotesList.size() == 0) {
            log.info("redis update 시작");
            updateLifeQuotes(phraseKey);
        }

        return todayLifeQuotesList;
    }

    private void updateLifeQuotes(String key) {
        List<TodayLifeQuotes> todayLifeQuotesList = billionaireRepository.findLifeQuotesByIds(getRandomIndex());
        billionaireRepository.saveLifeQuotesList(key, getTTL(6, 0, LocalDateTime.now()), todayLifeQuotesList);
    }



    private List<Long> getRandomIndex() {
        List<Long> randomIndexList = new ArrayList<>();
        Random random = new Random(); // 랜덤 객체 생성
        random.setSeed(System.currentTimeMillis());

        int count = 3;
        int[] randomIndexArray = new int[count];

        for (int i = 0; i < count; i++) {
            randomIndexArray[i] = random.nextInt(10) + 1;
            for (int j = 0; j < i; j++) {
                if (randomIndexArray[j] == randomIndexArray[i]) {
                    i--;
                }
            }
        }

        for (int i = 0; i < count; i++) {
            randomIndexList.add(((randomIndexArray[i] - 1) * 10L) + (random.nextInt(10) + 1));
        }
        log.info("생성된 랜덤 index : {}", randomIndexList);

        return randomIndexList;
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
