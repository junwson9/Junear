package hasix.junear.corporation.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewCorporationDetailsAuthenticatedResponse {

    private Long corporationId;
    private Long industryId;
    private String industryType;
    private String corporationCode;
    private String name;
    private Double stabilityRank;
    private Double growthRank;
    private Double profitabilityRank;
    private Double activityRank;
    private Double totalRank;
    private Long stockClose;
    private Boolean isBookmarked;


    @Builder
    public ViewCorporationDetailsAuthenticatedResponse(Long corporationId, Long industryId,
            String industryType, String corporationCode,
            String name, Double stabilityRank, Double growthRank, Double profitabilityRank,
            Double activityRank,
            Double totalRank, Long stockClose, Boolean isBookmarked) {
        this.corporationId = corporationId;
        this.industryId = industryId;
        this.industryType = industryType;
        this.corporationCode = corporationCode;
        this.name = name;
        this.stabilityRank = stabilityRank;
        this.growthRank = growthRank;
        this.profitabilityRank = profitabilityRank;
        this.activityRank = activityRank;
        this.totalRank = totalRank;
        this.stockClose = stockClose;
        this.isBookmarked = isBookmarked;
    }

    public static ViewCorporationDetailsAuthenticatedResponse from(boolean isBookmarkedResult,
            ViewCorporationDetailsResponse findCorporationResult) {
        return ViewCorporationDetailsAuthenticatedResponse.builder()
                                                          .corporationId(
                                                                  findCorporationResult.getCorporationId())
                                                          .industryId(
                                                                  findCorporationResult.getIndustryId())
                                                          .industryType(
                                                                  findCorporationResult.getIndustryType())
                                                          .corporationCode(
                                                                  findCorporationResult.getCorporationCode())
                                                          .name(findCorporationResult.getName())
                                                          .stabilityRank(
                                                                  findCorporationResult.getStabilityRank())
                                                          .growthRank(
                                                                  findCorporationResult.getGrowthRank())
                                                          .profitabilityRank(
                                                                  findCorporationResult.getProfitabilityRank())
                                                          .activityRank(
                                                                  findCorporationResult.getActivityRank())
                                                          .totalRank(
                                                                  findCorporationResult.getTotalRank())
                                                          .stockClose(
                                                                  findCorporationResult.getStockClose())
                                                          .isBookmarked(isBookmarkedResult)
                                                          .build();
    }
}
