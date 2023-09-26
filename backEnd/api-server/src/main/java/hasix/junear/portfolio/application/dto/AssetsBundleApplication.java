package hasix.junear.portfolio.application.dto;

import hasix.junear.portfolio.api.dto.AssetsBundle;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AssetsBundleApplication {

    private Long totalAssets;
    private Long totalInvestment;
    private Long profitAndLoss;

    @Builder
    public AssetsBundleApplication(Long totalAssets, Long totalInvestment, Long profitAndLoss) {
        this.totalAssets = totalAssets;
        this.totalInvestment = totalInvestment;
        this.profitAndLoss = profitAndLoss;
    }

    public static AssetsBundleApplication from(List<PortfolioBundleApplication> portfolioBundleApplicationList) {

        Long calculatedTotalAssets = assetsCalculator(portfolioBundleApplicationList);
        Long calculatedTotalInvestment = investmentCalculator(portfolioBundleApplicationList);
        Long calculatedProfitAndLoss = profitAndLossCalculator(calculatedTotalAssets,
                calculatedTotalInvestment);

        return AssetsBundleApplication.builder()
                                      .totalAssets(calculatedTotalAssets)
                                      .totalInvestment(calculatedTotalInvestment)
                                      .profitAndLoss(calculatedProfitAndLoss)
                                      .build();
    }

    private static Long assetsCalculator(List<PortfolioBundleApplication> portfolioBundleApplicationList) {

        return portfolioBundleApplicationList.stream()
                                             .mapToLong(
                                          portfolio -> portfolio.getStockCount()
                                                  * portfolio.getStockClose())
                                             .sum();
    }

    private static Long investmentCalculator(List<PortfolioBundleApplication> portfolioBundleApplicationList) {

        return portfolioBundleApplicationList.stream()
                                             .mapToLong(
                                          portfolio -> portfolio.getStockCount()
                                                  * portfolio.getAveragePrice())
                                             .sum();
    }

    private static Long profitAndLossCalculator(Long calculatedTotalAssets,
            Long calculatedTotalInvestment) {

        return calculatedTotalAssets - calculatedTotalInvestment;
    }

    public static AssetsBundle toAssetsBundle(AssetsBundleApplication assetsBundleApplication) {
        return AssetsBundle.builder()
                .totalAssets(assetsBundleApplication.totalAssets)
                .totalInvestment(assetsBundleApplication.totalInvestment)
                .profitAndLoss(assetsBundleApplication.profitAndLoss)
                .build();
    }
}
