package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class PortFolioModifyApiRequest {

    private Long corporationId;

    @NotEmpty(message = "수량을 반드시 입력해야 합니다.")
    private Long stockCount;

    @NotEmpty(message = "평단가를 반드시 입력해야 합니다.")
    private Long averagePrice;

    @Builder
    public PortFolioModifyApiRequest(Long corporationId, Long stockCount, Long averagePrice) {
        this.corporationId = corporationId;
        this.stockCount = stockCount;
        this.averagePrice = averagePrice;
    }

}
