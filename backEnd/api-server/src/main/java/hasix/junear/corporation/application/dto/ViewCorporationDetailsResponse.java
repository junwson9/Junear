package hasix.junear.corporation.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewCorporationDetailsResponse {

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
    public ViewCorporationDetailsResponse(Long corporationId, Long industryId, String industryType, String corporationCode,
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
}
