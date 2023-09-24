package hasix.junear.portfolio.application;

import hasix.junear.portfolio.application.dto.CreatePortfolioRequest;
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

    public void createPortfolio(List<CreatePortfolioRequest> requestList) {

        portfolioRepository.saveAll(requestList.stream()
                .map(CreatePortfolioRequest::toPortfolio)
                .collect(Collectors.toList()));
    }
}