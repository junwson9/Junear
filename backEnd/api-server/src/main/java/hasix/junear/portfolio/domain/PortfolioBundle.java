package hasix.junear.portfolio.domain;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.exception.CorporationErrorCode;
import hasix.junear.corporation.exception.CorporationException;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PortfolioBundle {

    //노출 정보_기업
    private Long corporationId;
    private Long industryId;
    private String industryType;
    private String corporationCode;
    private String name;
    private String totalRank;
    private Long stockClose;
    //비노출 정보_기업
    private Long stabilityRank;
    private Long growthRank;
    private Long profitabilityRank;
    private Long activityRank;
    //노출 정보_포트폴리오
    private Long stockCount;
    private Long averagePrice;

    @Builder
    public PortfolioBundle(Long corporationId, Long industryId, String industryType,
            String corporationCode, String name, String totalRank, Long stockClose,
            Long stabilityRank,
            Long growthRank, Long profitabilityRank, Long activityRank, Long stockCount,
            Long averagePrice) {
        this.corporationId = corporationId;
        this.industryId = industryId;
        this.industryType = industryType;
        this.corporationCode = corporationCode;
        this.name = name;
        this.totalRank = totalRank;
        this.stockClose = stockClose;
        this.stabilityRank = stabilityRank;
        this.growthRank = growthRank;
        this.profitabilityRank = profitabilityRank;
        this.activityRank = activityRank;
        this.stockCount = stockCount;
        this.averagePrice = averagePrice;
    }

    public static PortfolioBundle from(
            Optional<ViewCorporationDetailsResponse> viewCorporationDetailsResponseById,
            Portfolio portfolio) {

        ViewCorporationDetailsResponse corporationDetails = viewCorporationDetailsResponseById.orElseThrow(
                () -> new CorporationException(
                        CorporationErrorCode.NOT_FOUND_CORPORATION));
        double totalRankNumber = corporationDetails.getTotalRank();

        return PortfolioBundle.builder()
                              .corporationId(corporationDetails.getCorporationId())
                              .industryId(corporationDetails.getIndustryId())
                              .industryType(corporationDetails.getIndustryType())
                              .corporationCode(corporationDetails.getCorporationCode())
                              .name(corporationDetails.getName())
                              .totalRank(classifyGradeByRange(corporationDetails.getTotalRank()))
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
}

