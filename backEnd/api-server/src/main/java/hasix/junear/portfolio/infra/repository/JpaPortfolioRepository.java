package hasix.junear.portfolio.infra.repository;

import hasix.junear.portfolio.domain.Portfolio;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByMemberIdAndCorporationId(Long memberId, Long corporationId);

    void deleteById (Long portfolioId);

    List<Portfolio> findAllByMemberId(Long memberId);
}