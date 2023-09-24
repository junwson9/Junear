package hasix.junear.portfolio.infra.repository;

import hasix.junear.portfolio.domain.Portfolio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Query("SELECT p FROM Portfolio p WHERE p.memberId = :memberId AND p.corporationId = :corporationId")
    Optional<Portfolio> duplicateVerifiedByMemberIdAndCorporationId(Long memberId, Long corporationId);

    @Modifying
    @Query("DELETE FROM Portfolio p WHERE p.memberId = :memberId AND p.corporationId = :corporationId")
    void deleteByMemberIdAndCorporationId (Long memberId, Long corporationId);
}