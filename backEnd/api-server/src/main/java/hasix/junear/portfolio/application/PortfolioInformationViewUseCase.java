package hasix.junear.portfolio.application;

import hasix.junear.portfolio.application.dto.ViewPortfolioInformationResponse;
import hasix.junear.portfolio.domain.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioInformationViewUseCase {

    private final PortfolioRepository portfolioRepository;

    public ViewPortfolioInformationResponse getPortfolio(Long memberId) {


        return null;
    }
}
