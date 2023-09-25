package hasix.junear.portfolio.application.dto;

import hasix.junear.portfolio.api.dto.PortFolioModifyApiRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ModifyEachPortfolioRequest {

    private Long memberId;
    private Long corporationId;
    private Long stockCount;
    private Long averagePrice;

    @Builder
    public ModifyEachPortfolioRequest(Long memberId, Long corporationId, Long stockCount,
            Long averagePrice) {
        this.memberId = memberId;
        this.corporationId = corporationId;
        this.stockCount = stockCount;
        this.averagePrice = averagePrice;
    }

    public static ModifyEachPortfolioRequest from(Long memberId,
            PortFolioModifyApiRequest request) {

        return ModifyEachPortfolioRequest.builder()
                                         .memberId(memberId)
                                         .corporationId(request.getCorporationId())
                                         .stockCount(request.getStockCount())
                                         .averagePrice(request.getAveragePrice())
                                         .build();
    }
}
