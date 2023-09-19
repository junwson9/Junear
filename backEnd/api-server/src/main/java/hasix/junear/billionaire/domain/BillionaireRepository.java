package hasix.junear.billionaire.domain;

import hasix.junear.billionaire.application.dto.TodayLifeQuotes;

import java.util.List;

public interface BillionaireRepository {
    List<TodayLifeQuotes> findLifeQuotes(String key);
    List<TodayLifeQuotes> findLifeQuotesByIds(List<Long> ids);
    void saveLifeQuotesList(String key, Long ttl, List<TodayLifeQuotes> todayLifeQuotesList);
}
