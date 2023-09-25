package hasix.junear.portfolio.infra.repository;

import hasix.junear.portfolio.domain.Portfolio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByMemberIdAndCorporationId(Long memberId, Long corporationId);

    void deleteByMemberIdAndCorporationId (Long memberId, Long corporationId);
}