package hasix.junear.corporation.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class CorporationDetailsApiResponse {

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

    @Builder
    public CorporationDetailsApiResponse(Long corporationId, Long industryId, String industryType, String corporationCode,
            String name, Double stabilityRank, Double growthRank, Double profitabilityRank,
            Double activityRank,
            Double totalRank, Long stockClose) {
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
    }

    public static CorporationDetailsApiResponse from(ViewCorporationDetailsResponse result){
        return CorporationDetailsApiResponse.builder()
                                            .corporationId(result.getCorporationId())
                                            .industryId(result.getIndustryId())
                                            .industryType(result.getIndustryType())
                                            .corporationCode(result.getCorporationCode())
                                            .name(result.getName())
                                            .stabilityRank(result.getStabilityRank())
                                            .growthRank(result.getGrowthRank())
                                            .profitabilityRank(result.getProfitabilityRank())
                                            .activityRank(result.getActivityRank())
                                            .totalRank(result.getTotalRank())
                                            .stockClose(result.getStockClose())
                                            .build();
    }
}
