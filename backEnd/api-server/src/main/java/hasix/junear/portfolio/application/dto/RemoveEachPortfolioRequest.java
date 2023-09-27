package hasix.junear.portfolio.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveEachPortfolioRequest {

    //    private Long memberId;
//    private Long corporationId;
    private Long portfolioId;

//    @Builder
//    public RemoveEachPortfolioRequest(Long memberId, Long corporationId) {
//        this.memberId = memberId;
//        this.corporationId = corporationId;
//    }

    @Builder
    public RemoveEachPortfolioRequest(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    //    public static RemoveEachPortfolioRequest from(Long memberId, Long corporationId) {
    public static RemoveEachPortfolioRequest from(Long portfolioId) {

//        return RemoveEachPortfolioRequest.builder()
//                                         .memberId(memberId)
//                                         .corporationId(corporationId)
//                                         .build();

        return RemoveEachPortfolioRequest.builder()
                                         .portfolioId(portfolioId)
                                         .build();
    }
}
