package hasix.junear.portfolio.application.dto;

import hasix.junear.portfolio.api.dto.PortFolioAddApiRequest;
import hasix.junear.portfolio.domain.Portfolio;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddEachPortfolioRequest {

    private Long memberId;
    private Long corporationId;
    private Long stockCount;
    private Long averagePrice;

    @Builder
    public AddEachPortfolioRequest(Long memberId, Long corporationId, Long stockCount,
            Long averagePrice) {
        this.memberId = memberId;
        this.corporationId = corporationId;
        this.stockCount = stockCount;
        this.averagePrice = averagePrice;
    }

    public static AddEachPortfolioRequest from(Long memberId, PortFolioAddApiRequest request) {
        return AddEachPortfolioRequest.builder()
                                      .memberId(memberId)
                                      .corporationId(request.getCorporationId())
                                      .stockCount(request.getStockCount())
                                      .averagePrice(request.getAveragePrice())
                                      .build();
    }

    public static Portfolio toPortfolio(AddEachPortfolioRequest request) {

        return Portfolio.builder()
                        .memberId(request.getMemberId())
                        .corporationId(request.getCorporationId())
                        .stockCount(request.getStockCount())
                        .averagePrice(request.getAveragePrice())
                        .build();
    }

}
