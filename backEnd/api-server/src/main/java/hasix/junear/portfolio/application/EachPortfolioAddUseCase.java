package hasix.junear.portfolio.application;

import hasix.junear.portfolio.application.dto.AddEachPortfolioRequest;
import hasix.junear.portfolio.domain.PortfolioRepository;
import hasix.junear.portfolio.exception.PortfolioErrorCode;
import hasix.junear.portfolio.exception.PortfolioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EachPortfolioAddUseCase {

    private final PortfolioRepository portfolioRepository;

    public void addPortfolio(AddEachPortfolioRequest request) {

        portfolioRepository.findByMemberIdAndCorporationId(request.getMemberId(),
                request.getCorporationId()).ifPresentOrElse(
                portfolioExist -> {
                    throw new PortfolioException(PortfolioErrorCode.ALREADY_EXIST_PORTFOLIO);
                },
                () -> portfolioRepository.save(AddEachPortfolioRequest.toPortfolio(request))
        );
    }
}
