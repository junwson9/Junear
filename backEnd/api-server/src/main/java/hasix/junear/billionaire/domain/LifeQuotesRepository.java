package hasix.junear.billionaire.domain;

import java.util.Optional;

public interface LifeQuotesRepository {
    Long countByBillionaireId(Long id);
}
