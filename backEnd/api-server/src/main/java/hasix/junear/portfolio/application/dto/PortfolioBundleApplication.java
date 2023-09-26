package hasix.junear.portfolio.application.dto;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.exception.CorporationErrorCode;
import hasix.junear.corporation.exception.CorporationException;
import hasix.junear.portfolio.api.dto.PortfolioBundle;
import hasix.junear.portfolio.domain.Portfolio;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PortfolioBundleApplication {

    //노출 정보_기업
    private Long corporationId;
    private Long industryId;
    private String industryType;
    private String corporationCode;
    private String name;
    private Double totalRankNumber;
    private String totalRankString;
    private Long stockClose;
    //비노출 정보_기업
    private Double stabilityRank;
    private Double growthRank;
    private Double profitabilityRank;
    private Double activityRank;
    //노출 정보_포트폴리오
    private Long stockCount;
    private Long averagePrice;

    @Builder
    public PortfolioBundleApplication(Long corporationId, Long industryId, String industryType,
            String corporationCode, String name, Double totalRankNumber, String totalRankString,
            Long stockClose,
            Double stabilityRank,
            Double growthRank, Double profitabilityRank, Double activityRank, Long stockCount,
            Long averagePrice) {
        this.corporationId = corporationId;
        this.industryId = industryId;
        this.industryType = industryType;
        this.corporationCode = corporationCode;
        this.name = name;
        this.totalRankNumber = totalRankNumber;
        this.totalRankString = totalRankString;
        this.stockClose = stockClose;
        this.stabilityRank = stabilityRank;
        this.growthRank = growthRank;
        this.profitabilityRank = profitabilityRank;
        this.activityRank = activityRank;
        this.stockCount = stockCount;
        this.averagePrice = averagePrice;
    }

    public static PortfolioBundleApplication from(
            Optional<ViewCorporationDetailsResponse> viewCorporationDetailsResponseById,
            Portfolio portfolio) {

        ViewCorporationDetailsResponse corporationDetails = viewCorporationDetailsResponseById.orElseThrow(
                () -> new CorporationException(
                        CorporationErrorCode.NOT_FOUND_CORPORATION));

        return PortfolioBundleApplication.builder()
                                         .corporationId(corporationDetails.getCorporationId())
                                         .industryId(corporationDetails.getIndustryId())
                                         .industryType(corporationDetails.getIndustryType())
                                         .corporationCode(corporationDetails.getCorporationCode())
                                         .name(corporationDetails.getName())
                                         .totalRankNumber(corporationDetails.getTotalRank())
                                         .totalRankString(
                                      classifyGradeByRange(corporationDetails.getTotalRank()))
                                         .stockClose(corporationDetails.getStockClose())
                                         .stabilityRank(corporationDetails.getStabilityRank())
                                         .growthRank(corporationDetails.getGrowthRank())
                                         .profitabilityRank(corporationDetails.getProfitabilityRank())
                                         .activityRank(corporationDetails.getActivityRank())
                                         .stockCount(portfolio.getStockCount())
                                         .averagePrice(portfolio.getAveragePrice())
                                         .build();
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

    public static PortfolioBundle toPortfolioBundle(PortfolioBundleApplication portfolioBundleApplication) {

        return PortfolioBundle.builder()
                .corporationId(portfolioBundleApplication.getCorporationId())
                .industryId(portfolioBundleApplication.getIndustryId())
                .industryType(portfolioBundleApplication.getIndustryType())
                .corporationCode(portfolioBundleApplication.getCorporationCode())
                .name(portfolioBundleApplication.getName())
                .totalRankNumber(portfolioBundleApplication.getTotalRankNumber())
                .totalRankString(portfolioBundleApplication.getTotalRankString())
                .stockClose(portfolioBundleApplication.getStockClose())
                .stabilityRank(portfolioBundleApplication.getStabilityRank())
                .growthRank(portfolioBundleApplication.getGrowthRank())
                .profitabilityRank(portfolioBundleApplication.getProfitabilityRank())
                .activityRank(portfolioBundleApplication.getActivityRank())
                .stockCount(portfolioBundleApplication.getStockCount())
                .averagePrice(portfolioBundleApplication.getAveragePrice())
                .build();
    }
}

