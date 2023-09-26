package hasix.junear.portfolio.application;

import hasix.junear.portfolio.application.dto.ModifyEachPortfolioRequest;
import hasix.junear.portfolio.domain.Portfolio;
import hasix.junear.portfolio.domain.PortfolioRepository;
import hasix.junear.portfolio.exception.PortfolioErrorCode;
import hasix.junear.portfolio.exception.PortfolioException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EachPortfolioModifyUseCase {

    private final PortfolioRepository portfolioRepository;

    public void updatePortfolio(ModifyEachPortfolioRequest request) {

        Portfolio findPortfolio = portfolioRepository.findByMemberIdAndCorporationId(
                request.getMemberId(), request.getCorporationId()).orElseThrow(() -> new PortfolioException(
                PortfolioErrorCode.NOT_FOUND_PORTFOLIO));

        Optional.ofNullable(request.getStockCount())
                .ifPresent(findPortfolio::setStockCount);
        Optional.ofNullable(request.getAveragePrice())
                .ifPresent(findPortfolio::setAveragePrice);

        portfolioRepository.save(findPortfolio);
    }
}
