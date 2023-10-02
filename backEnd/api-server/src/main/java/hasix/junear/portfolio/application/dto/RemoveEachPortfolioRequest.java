package hasix.junear.portfolio.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveEachPortfolioRequest {

    private Long portfolioId;

    @Builder
    public RemoveEachPortfolioRequest(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public static RemoveEachPortfolioRequest from(Long portfolioId) {

        return RemoveEachPortfolioRequest.builder()
                                         .portfolioId(portfolioId)
                                         .build();
    }
}
