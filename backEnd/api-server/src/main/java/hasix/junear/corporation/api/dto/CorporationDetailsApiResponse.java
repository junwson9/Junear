package hasix.junear.corporation.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CorporationDetailsApiResponse {

    private Long corporationId;
    private Long industryId;
    private Long corporationCode;
    private String name;
    private Long stabilityRank;
    private Long growthRank;
    private Long profitabilityRank;
    private Long activityRank;
    private Double totalRank;
    private Long stockClose;

    @Builder
    public CorporationDetailsApiResponse(Long corporationId, Long industryId, Long corporationCode,
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

    public static CorporationDetailsApiResponse from(ViewCorporationDetailsResponse result){
        return CorporationDetailsApiResponse.builder()
                                            .corporationId(result.getCorporationId())
                                            .industryId(result.getIndustryId())
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
