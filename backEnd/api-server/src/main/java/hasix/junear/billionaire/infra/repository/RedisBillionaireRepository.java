package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.application.dto.TodayLifeQuotes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisBillionaireRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ListOperations<String, Object> listOperations;

    public void saveListToRedis(String key, Long ttl, List<TodayLifeQuotes> todayLifeQuotesList) {
        listOperations.leftPushAll(key, todayLifeQuotesList.toArray(new TodayLifeQuotes[0]));
        redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
        log.info("redis 저장 완료");
    }

    public List<TodayLifeQuotes> findBillionaireLifeQuotesByKey(String key) {
        log.info("redis 데이터 조회 시작");
        List<Object> resultList = listOperations.range(key, 0, -1);

        return resultList.stream()
                .map(data -> (TodayLifeQuotes) data)
                .collect(Collectors.toList());
    }



}
