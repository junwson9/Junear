package hasix.junear.billionaire.domain;

import hasix.junear.billionaire.application.dto.TodayLifeQuotes;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public interface BillionaireRepository {
    List<TodayLifeQuotes> findLifeQuotes(String key);
    List<TodayLifeQuotes> findLifeQuotesByIds(List<Long> ids);
    void saveLifeQuotesList(String key, Long ttl, List<TodayLifeQuotes> todayLifeQuotesList);
    Long countById();
}
