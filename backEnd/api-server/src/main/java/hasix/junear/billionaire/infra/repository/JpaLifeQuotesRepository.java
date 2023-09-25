package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.domain.LifeQuotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaLifeQuotesRepository extends JpaRepository<LifeQuotes, Long> {
    Long countByBillionaireId(Long billionaireId);
}
