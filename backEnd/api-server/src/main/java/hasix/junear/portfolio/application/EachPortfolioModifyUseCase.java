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

        verifyRequest(request);

        portfolioRepository.save(modifyStockCountOrAveragePrice(request));

    }

    private void verifyRequest(ModifyEachPortfolioRequest request) {

        portfolioRepository.findById(request.getPortfolioId())
                           .orElseThrow(() -> new PortfolioException(
                                   PortfolioErrorCode.NOT_FOUND_PORTFOLIO));

    }

    private Portfolio modifyStockCountOrAveragePrice(ModifyEachPortfolioRequest request) {

        return Portfolio.builder()
                        .id(request.getPortfolioId())
                        .memberId(request.getMemberId())
                        .corporationId(request.getCorporationId())
                        .stockCount(request.getStockCount())
                        .averagePrice(request.getAveragePrice())
                        .build();
    }
}
