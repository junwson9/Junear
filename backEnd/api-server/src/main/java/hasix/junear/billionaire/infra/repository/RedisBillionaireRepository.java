package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.application.dto.BillionaireLifeQuotesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisBillionaireRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ListOperations<String, Object> listOperations;

    public void saveListToRedis(List<BillionaireLifeQuotesResponse> responseList, String key) {
        log.info("redis에 데이터 갱신");
        listOperations.leftPushAll(key, responseList.toArray(new BillionaireLifeQuotesResponse[0]));
        redisTemplate.expire(key, getTTL(), TimeUnit.SECONDS);
    }

    public List<BillionaireLifeQuotesResponse> findBillionaireLifeQuotesByKey(String key) {
        log.info("redis에서 데이터 조회");
        List<Object> resultList = listOperations.range(key, 0, -1);

        return resultList.stream()
                .map(data -> (BillionaireLifeQuotesResponse) data)
                .collect(Collectors.toList());
    }

    // 현재 시각부터 다음 아침 6시까지 남은 시간(초) 계산
    private long getTTL() {
        LocalTime next6AM = LocalTime.of(6, 0);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next6AMDateTime = now.with(next6AM);

        if (now.toLocalTime().isAfter(next6AM)) {
            // 이미 6시를 지난 경우 다음 날 아침 6시로 설정
            next6AMDateTime = next6AMDateTime.plusDays(1);
        }

        // 현재 시각과 다음 아침 6시의 LocalDateTime을 계산하여 TTL 설정
        long secondsUntilNext6AM = Duration.between(now, next6AMDateTime).getSeconds();

        return secondsUntilNext6AM;
    }
}
