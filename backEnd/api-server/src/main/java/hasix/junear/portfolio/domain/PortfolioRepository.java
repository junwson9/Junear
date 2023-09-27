package hasix.junear.portfolio.domain;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository {

    Optional<Portfolio> findById (Long id);

    Portfolio save (Portfolio portfolio);

    Optional<Portfolio> findByMemberIdAndCorporationId(Long memberId, Long corporationId);

    void deleteById (Long portfolioId);

    List<Portfolio> findAllByMemberId(Long memberId);
}
