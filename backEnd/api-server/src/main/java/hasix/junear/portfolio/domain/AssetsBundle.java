package hasix.junear.portfolio.domain;

import java.util.List;
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

    public static AssetsBundle from(List<PortfolioBundle> portfolioBundleList) {

        Long calculatedTotalAssets = assetsCalculator(portfolioBundleList);
        Long calculatedTotalInvestment = investmentCalculator(portfolioBundleList);
        Long calculatedProfitAndLoss = profitAndLossCalculator(calculatedTotalAssets,
                calculatedTotalInvestment);

        return AssetsBundle.builder()
                           .totalAssets(calculatedTotalAssets)
                           .totalInvestment(calculatedTotalInvestment)
                           .profitAndLoss(calculatedProfitAndLoss)
                           .build();
    }

    private static Long assetsCalculator(List<PortfolioBundle> portfolioBundleList) {

        return portfolioBundleList.stream()
                                  .mapToLong(
                                          portfolio -> portfolio.getStockCount()
                                                  * portfolio.getStockClose())
                                  .sum();
    }

    private static Long investmentCalculator(List<PortfolioBundle> portfolioBundleList) {

        return portfolioBundleList.stream()
                                  .mapToLong(
                                          portfolio -> portfolio.getStockCount()
                                                  * portfolio.getAveragePrice())
                                  .sum();
    }

    private static Long profitAndLossCalculator(Long calculatedTotalAssets,
            Long calculatedTotalInvestment) {

        return calculatedTotalAssets - calculatedTotalInvestment;
    }
}
