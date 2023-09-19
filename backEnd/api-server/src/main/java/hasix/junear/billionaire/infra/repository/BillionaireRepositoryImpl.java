package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.application.dto.BillionaireLifeQuotesResponse;
import hasix.junear.billionaire.domain.BillionaireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BillionaireRepositoryImpl implements BillionaireRepository {

    private final MysqlJpaBillionaireRepository mysqlJpaBillionaireRepository;
    private final RedisBillionaireRepository redisBillionaireRepository;

    @Override
    public List<BillionaireLifeQuotesResponse> findBillionaireLifeQuotes(String key) {
        List<BillionaireLifeQuotesResponse> list = redisBillionaireRepository.findBillionaireLifeQuotesByKey(key);

        if (list.size() == 0) {
            log.info("redis에 데이터가 존재하지 않음");
            // mysql에서 데이터 조회
            list = mysqlJpaBillionaireRepository.findBillionaireLifeQuotesByIds(getRandomIndex());
            if (list.size() == 0 || list == null) {
                log.error("데이터 조회 실패");
            }
            redisBillionaireRepository.saveListToRedis(list, key);
        }
        return list;
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

}
