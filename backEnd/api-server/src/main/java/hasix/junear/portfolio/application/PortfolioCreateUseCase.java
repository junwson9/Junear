package hasix.junear.portfolio.application;

import hasix.junear.portfolio.application.dto.AddEachPortfolioRequest;
import hasix.junear.portfolio.application.dto.CreatePortfolioRequest;
import hasix.junear.portfolio.domain.Portfolio;
import hasix.junear.portfolio.domain.PortfolioRepository;
import hasix.junear.portfolio.exception.PortfolioErrorCode;
import hasix.junear.portfolio.exception.PortfolioException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioCreateUseCase {

    private final PortfolioRepository portfolioRepository;

    public void createPortfolio(Long memberId, List<CreatePortfolioRequest> requestList) {

        verifyPortfolio(memberId);
        requestList.forEach(this::addPortfolio);

    }

    private void verifyPortfolio(Long memberId) {

        List<Portfolio> portfolioList = portfolioRepository.findAllByMemberId(memberId);
        if(portfolioList != null && !portfolioList.isEmpty()) throw new PortfolioException(PortfolioErrorCode.ALREADY_OWN_PORTFOLIO);
    }

    public void addPortfolio(CreatePortfolioRequest request) {

        portfolioRepository.findByMemberIdAndCorporationId(request.getMemberId(),
                request.getCorporationId()).ifPresentOrElse(
                portfolioExist -> {
                    throw new PortfolioException(PortfolioErrorCode.ALREADY_EXIST_PORTFOLIO);
                },
                () -> portfolioRepository.save(CreatePortfolioRequest.toPortfolio(request))
        );

    }
}
