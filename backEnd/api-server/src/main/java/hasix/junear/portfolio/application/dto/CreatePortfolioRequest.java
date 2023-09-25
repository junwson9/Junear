package hasix.junear.portfolio.application.dto;

import hasix.junear.portfolio.api.dto.PortFolioAddApiRequest;
import hasix.junear.portfolio.domain.Portfolio;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreatePortfolioRequest {

    private Long memberId;
    private Long corporationId;
    private Long stockCount;
    private Long averagePrice;

    @Builder
    public CreatePortfolioRequest(Long memberId, Long corporationId, Long stockCount,
            Long averagePrice) {
        this.memberId = memberId;
        this.corporationId = corporationId;
        this.stockCount = stockCount;
        this.averagePrice = averagePrice;
    }

    public static List<CreatePortfolioRequest> from(Long memberId,
            List<PortFolioAddApiRequest> requestList) {

        return requestList.stream()
                          .map(request -> CreatePortfolioRequest.builder()
                                                                .memberId(memberId)
                                                                .corporationId(request.getCorporationId())
                                                                .stockCount(request.getStockCount())
                                                                .averagePrice(request.getAveragePrice())
                                                                .build())
                        .collect(Collectors.toList());
    }

    public static Portfolio toPortfolio (CreatePortfolioRequest request){

        return Portfolio.builder()
                        .memberId(request.getMemberId())
                        .corporationId(request.getCorporationId())
                        .stockCount(request.getStockCount())
                        .averagePrice(request.getAveragePrice())
                        .build();
    }

}
