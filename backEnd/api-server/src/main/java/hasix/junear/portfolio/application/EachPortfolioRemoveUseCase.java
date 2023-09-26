package hasix.junear.portfolio.application;

import hasix.junear.portfolio.application.dto.RemoveEachPortfolioRequest;
import hasix.junear.portfolio.domain.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EachPortfolioRemoveUseCase {

    private final PortfolioRepository portfolioRepository;

    public void deletePortfolio(RemoveEachPortfolioRequest request) {
        portfolioRepository.deleteByMemberIdAndCorporationId(request.getMemberId(), request.getCorporationId());
    }
}
