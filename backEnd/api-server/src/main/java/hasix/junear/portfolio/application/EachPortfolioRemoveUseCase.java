package hasix.junear.portfolio.application;

import hasix.junear.portfolio.application.dto.RemoveEachPortfolioRequest;
import hasix.junear.portfolio.domain.Portfolio;
import hasix.junear.portfolio.domain.PortfolioRepository;
import hasix.junear.portfolio.exception.PortfolioErrorCode;
import hasix.junear.portfolio.exception.PortfolioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EachPortfolioRemoveUseCase {

    private final PortfolioRepository portfolioRepository;

    public void deletePortfolio(RemoveEachPortfolioRequest request) {

        Portfolio findPortfolio = portfolioRepository.findById(request.getPortfolioId()).orElseThrow(() -> new PortfolioException(
                PortfolioErrorCode.NOT_FOUND_PORTFOLIO));

        portfolioRepository.deleteById(findPortfolio.getId());

    }
}
