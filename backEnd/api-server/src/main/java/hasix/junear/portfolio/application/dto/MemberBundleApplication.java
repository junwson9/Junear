package hasix.junear.portfolio.application.dto;

import hasix.junear.portfolio.api.dto.EachRank;
import hasix.junear.portfolio.api.dto.MemberBundle;
import java.util.List;
import java.util.function.ToDoubleFunction;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberBundleApplication {

    //등급
    private String totalPortfolioRank;
    private EachRankApplication eachRankApplication;
    //금액
    private Long totalAssets;
    private List<EachAssetsApplication> eachAssetApplications;
    //지표
    private String portfolioStability;
    private String portfolioGrowth;
    private String portfolioProfitability;
    private String portfolioActivity;

    @Builder
    public MemberBundleApplication(String totalPortfolioRank, EachRankApplication eachRankApplication, Long totalAssets,
            List<EachAssetsApplication> eachAssetApplications, String portfolioStability, String portfolioGrowth,
            String portfolioProfitability, String portfolioActivity) {
        this.totalPortfolioRank = totalPortfolioRank;
        this.eachRankApplication = eachRankApplication;
        this.totalAssets = totalAssets;
        this.eachAssetApplications = eachAssetApplications;
        this.portfolioStability = portfolioStability;
        this.portfolioGrowth = portfolioGrowth;
        this.portfolioProfitability = portfolioProfitability;
        this.portfolioActivity = portfolioActivity;
    }

    public static MemberBundleApplication from(List<PortfolioBundleApplication> portfolioBundleApplicationList) {

        EachRankApplication eachRankApplicationResult = countEachRank(
                portfolioBundleApplicationList);
        String totalPortfolioRankResult = calculateAverageRank(portfolioBundleApplicationList,
                PortfolioBundleApplication::getTotalRankNumber);

        List<EachAssetsApplication> eachAssetsApplicationListResult = portfolioBundleApplicationList.stream()
                                                                                                    .map(portfolioBundleApplication -> EachAssetsApplication.from(
                                                                           portfolioBundleApplication.getName(),
                                                                           portfolioBundleApplication.getStockCount(),
                                                                           portfolioBundleApplication.getStockClose()))
                                                                                                    .sorted((e1, e2) -> Double.compare(e2.getCorporationAsset(), e1.getCorporationAsset()))
                                                                                                    .toList();

        Long totalAssetsResult = eachAssetsApplicationListResult.stream()
                                                                .mapToLong(EachAssetsApplication::getCorporationAsset)
                                                                .sum();

        String portfolioStabilityResult = calculateAverageRank(portfolioBundleApplicationList,
                PortfolioBundleApplication::getStabilityRank);
        String portfolioGrowthResult = calculateAverageRank(portfolioBundleApplicationList,
                PortfolioBundleApplication::getGrowthRank);
        String portfolioProfitabilityResult = calculateAverageRank(portfolioBundleApplicationList,
                PortfolioBundleApplication::getProfitabilityRank);
        String portfolioActivityResult = calculateAverageRank(portfolioBundleApplicationList,
                PortfolioBundleApplication::getActivityRank);

        return MemberBundleApplication.builder()
                                      .totalPortfolioRank(totalPortfolioRankResult)
                                      .eachRankApplication(eachRankApplicationResult)
                                      .totalAssets(totalAssetsResult)
                                      .eachAssetApplications(eachAssetsApplicationListResult)
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

    private static EachRankApplication countEachRank(List<PortfolioBundleApplication> portfolioBundleApplicationList) {

        Long sCnt = 0L;
        Long aPlusCnt = 0L;
        Long aCnt = 0L;
        Long bPlusCnt = 0L;
        Long bCnt = 0L;
        Long cPlusCnt = 0L;
        Long cCnt = 0L;
        Long totalCnt = 0L;

        for (PortfolioBundleApplication portfolioBundleApplication : portfolioBundleApplicationList) {
            String totalRank = portfolioBundleApplication.getTotalRankString();

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

        return EachRankApplication.builder()
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

    private static Long assetsCalculator(List<PortfolioBundleApplication> portfolioBundleApplicationList) {

        return portfolioBundleApplicationList.stream()
                                             .mapToLong(
                                          portfolio -> portfolio.getStockCount()
                                                  * portfolio.getStockClose())
                                             .sum();
    }

    public static MemberBundle toMemberBundle(MemberBundleApplication memberBundleApplication) {
        return MemberBundle.builder()
                .totalPortfolioRank(memberBundleApplication.getTotalPortfolioRank())
                .eachRank(EachRankApplication.toEachRank(memberBundleApplication.getEachRankApplication()))
                .totalAssets(memberBundleApplication.getTotalAssets())
                .eachAssets()
                .portfolioStability(memberBundleApplication.getPortfolioStability())
                .portfolioGrowth(memberBundleApplication.getPortfolioGrowth())
                .portfolioProfitability(memberBundleApplication.getPortfolioProfitability())
                .portfolioActivity(memberBundleApplication.getPortfolioActivity())
                .build();
    }
}
