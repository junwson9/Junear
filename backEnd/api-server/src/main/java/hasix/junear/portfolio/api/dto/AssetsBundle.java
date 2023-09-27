package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class AssetsBundle {

    private Long totalAssets;
    private Long totalInvestment;
    private Long profitAndLoss;

    @Builder
    public AssetsBundle(Long totalAssets, Long totalInvestment, Long profitAndLoss) {
        this.totalAssets = totalAssets;
        this.totalInvestment = totalInvestment;
        this.profitAndLoss = profitAndLoss;
    }

}
