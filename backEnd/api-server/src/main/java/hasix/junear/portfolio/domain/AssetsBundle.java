package hasix.junear.portfolio.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
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
