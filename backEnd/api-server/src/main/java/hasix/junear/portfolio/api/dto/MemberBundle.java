package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import java.util.function.ToDoubleFunction;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class MemberBundle {

    //등급
    private String totalPortfolioRank;
    private EachRank eachRank;
    //금액
    private Long totalAssets;
    private List<EachAssets> eachAssets;
    //지표
    private String portfolioStability;
    private String portfolioGrowth;
    private String portfolioProfitability;
    private String portfolioActivity;

    @Builder
    public MemberBundle(String totalPortfolioRank, EachRank eachRank, Long totalAssets,
            List<EachAssets> eachAssets, String portfolioStability, String portfolioGrowth,
            String portfolioProfitability, String portfolioActivity) {
        this.totalPortfolioRank = totalPortfolioRank;
        this.eachRank = eachRank;
        this.totalAssets = totalAssets;
        this.eachAssets = eachAssets;
        this.portfolioStability = portfolioStability;
        this.portfolioGrowth = portfolioGrowth;
        this.portfolioProfitability = portfolioProfitability;
        this.portfolioActivity = portfolioActivity;
    }

    public static MemberBundle from(List<PortfolioBundle> portfolioBundleList) {

        EachRank eachRankResult = countEachRank(portfolioBundleList);
        String totalPortfolioRankResult = calculateAverageRank(portfolioBundleList,
                PortfolioBundle::getTotalRankNumber);

        List<EachAssets> eachAssetsListResult = portfolioBundleList.stream()
                                                                   .map(portfolioBundle -> EachAssets.from(
                                                                           portfolioBundle.getName(),
                                                                           portfolioBundle.getStockCount(),
                                                                           portfolioBundle.getStockClose()))
                                                                   .sorted((e1, e2) -> Double.compare(e2.getCorporationAsset(), e1.getCorporationAsset()))
                                                                   .toList();

        Long totalAssetsResult = eachAssetsListResult.stream()
                                                     .mapToLong(EachAssets::getCorporationAsset)
                                                     .sum();

        String portfolioStabilityResult = calculateAverageRank(portfolioBundleList,
                PortfolioBundle::getStabilityRank);
        String portfolioGrowthResult = calculateAverageRank(portfolioBundleList,
                PortfolioBundle::getGrowthRank);
        String portfolioProfitabilityResult = calculateAverageRank(portfolioBundleList,
                PortfolioBundle::getProfitabilityRank);
        String portfolioActivityResult = calculateAverageRank(portfolioBundleList,
                PortfolioBundle::getActivityRank);

        return MemberBundle.builder()
                           .totalPortfolioRank(totalPortfolioRankResult)
                           .eachRank(eachRankResult)
                           .totalAssets(totalAssetsResult)
                           .eachAssets(eachAssetsListResult)
                           .portfolioStability(portfolioStabilityResult)
                           .portfolioGrowth(portfolioGrowthResult)
                           .portfolioProfitability(portfolioProfitabilityResult)
                           .portfolioActivity(portfolioActivityResult)
                           .build();
    }

    private static <T> String calculateAverageRank(List<T> list, ToDoubleFunction<T> mapper) {

        double numerator = list.stream()
                               .mapToDouble(mapper)
                               .sum();
        int denominator = list.size();
        double totalPortfolioRankNumber = numerator / denominator;

        return classifyGradeByRange(totalPortfolioRankNumber);
    }

    private static String classifyGradeByRange(Double totalRankNumber) {
        if (6 < totalRankNumber && totalRankNumber <= 7) {
            return "s";
        } else if (5 < totalRankNumber && totalRankNumber <= 6) {
            return "aPlus";
        } else if (4 < totalRankNumber && totalRankNumber <= 5) {
            return "a";
        } else if (3 < totalRankNumber && totalRankNumber <= 4) {
            return "bPlus";
        } else if (2 < totalRankNumber && totalRankNumber <= 3) {
            return "b";
        } else if (1 < totalRankNumber && totalRankNumber <= 2) {
            return "cPlus";
        } else if (0 < totalRankNumber && totalRankNumber <= 1) {
            return "c";
        } else {
            return "not value";
        }
    }

    private static EachRank countEachRank(List<PortfolioBundle> portfolioBundleList) {

        Long sCnt = 0L;
        Long aPlusCnt = 0L;
        Long aCnt = 0L;
        Long bPlusCnt = 0L;
        Long bCnt = 0L;
        Long cPlusCnt = 0L;
        Long cCnt = 0L;
        Long totalCnt = 0L;

        for (PortfolioBundle portfolioBundle : portfolioBundleList) {
            String totalRank = portfolioBundle.getTotalRankString();

            totalCnt++;

            switch (totalRank) {
                case "s" -> sCnt++;
                case "aPlus" -> aPlusCnt++;
                case "a" -> aCnt++;
                case "bPlus" -> bPlusCnt++;
                case "b" -> bCnt++;
                case "cPlus" -> cPlusCnt++;
                case "c" -> cCnt++;
                default -> {
                }
            }
        }

        return EachRank.builder()
                       .s(sCnt)
                       .aPlus(aPlusCnt)
                       .a(aCnt)
                       .bPlus(bPlusCnt)
                       .b(bCnt)
                       .cPlus(cPlusCnt)
                       .c(cCnt)
                       .totalCount(totalCnt)
                       .build();
    }

    private static Long assetsCalculator(List<PortfolioBundle> portfolioBundleList) {

        return portfolioBundleList.stream()
                                  .mapToLong(
                                          portfolio -> portfolio.getStockCount()
                                                  * portfolio.getStockClose())
                                  .sum();
    }
}
