package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class PortFolioAddApiRequest {

    private Long corporationId;
    private Long stockCount;
    private Long averagePrice;

    @Builder
    public PortFolioAddApiRequest(Long corporationId, Long stockCount, Long averagePrice) {
        this.corporationId = corporationId;
        this.stockCount = stockCount;
        this.averagePrice = averagePrice;
    }

}
