package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.application.dto.TodayLifeQuotes;
import hasix.junear.billionaire.domain.BillionaireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BillionaireRepositoryImpl implements BillionaireRepository {

    private final MysqlJpaBillionaireRepository mysqlJpaBillionaireRepository;
    private final RedisBillionaireRepository redisBillionaireRepository;

    @Override
    public List<TodayLifeQuotes> findLifeQuotes(String key) {
        return redisBillionaireRepository.findBillionaireLifeQuotesByKey(key);
    }

    @Override
    public List<TodayLifeQuotes> findLifeQuotesByIds(List<Long> ids) {
        return mysqlJpaBillionaireRepository.findBillionaireLifeQuotesByIds(ids);
    }

    @Override
    public void saveLifeQuotesList(String key, Long ttl, List<TodayLifeQuotes> todayLifeQuotesList) {
        redisBillionaireRepository.saveListToRedis(key, ttl, todayLifeQuotesList);
    }
}
