package hasix.junear.portfolio.domain;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository {

    Optional<Portfolio> findById (Long id);

    Portfolio save (Portfolio portfolio);

    List<Portfolio> saveAll (List<Portfolio> portfolioList);

    Optional<Portfolio> findByMemberIdAndCorporationId(Long memberId, Long corporationId);

    void deleteByMemberIdAndCorporationId (Long memberId, Long corporationId);

    List<Portfolio> findAllByMemberId(Long memberId);
}
