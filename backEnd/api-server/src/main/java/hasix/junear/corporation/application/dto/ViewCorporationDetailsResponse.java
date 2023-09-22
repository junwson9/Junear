package hasix.junear.corporation.application.dto;

import hasix.junear.corporation.domain.Corporation;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewCorporationDetailsResponse {

    private Long corporationId;
    private Long industryId;
    private String corporationCode;
    private String name;
    private Long stabilityRank;
    private Long growthRank;
    private Long profitabilityRank;
    private Long activityRank;
    private Double totalRank;
    private Long stockClose;

    @Builder
    public ViewCorporationDetailsResponse(Long corporationId, Long industryId, String corporationCode,
            String name, Long stabilityRank, Long growthRank, Long profitabilityRank,
            Long activityRank,
            Double totalRank, Long stockClose) {
        this.corporationId = corporationId;
        this.industryId = industryId;
        this.corporationCode = corporationCode;
        this.name = name;
        this.stabilityRank = stabilityRank;
        this.growthRank = growthRank;
        this.profitabilityRank = profitabilityRank;
        this.activityRank = activityRank;
        this.totalRank = totalRank;
        this.stockClose = stockClose;
    }

    public static ViewCorporationDetailsResponse from(Corporation corporation) {
        return ViewCorporationDetailsResponse.builder()
                .corporationId(corporation.getId())
                .industryId(corporation.getIndustryId())
                .corporationCode(corporation.getCorporationCode())
                .name(corporation.getName())
                .stabilityRank(corporation.getStabilityRank())
                .growthRank(corporation.getGrowthRank())
                .profitabilityRank(corporation.getProfitabilityRank())
                .activityRank(corporation.getActivityRank())
                .totalRank(corporation.getTotalRank())
                .stockClose(corporation.getStockClose())
                .build();
    }
}
