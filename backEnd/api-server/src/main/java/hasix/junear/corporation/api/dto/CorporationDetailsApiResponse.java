package hasix.junear.corporation.api.dto;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CorporationDetailsApiResponse {

    private Long corporation_id;
    private Long industry_id;
    private String industry_type;
    private String corporation_code;
    private String name;
    private Double stability_rank;
    private Double growth_rank;
    private Double profitability_rank;
    private Double activity_rank;
    private Double total_rank;
    private Long stock_close;

    @Builder
    public CorporationDetailsApiResponse(Long corporation_id, Long industry_id, String industry_type, String corporation_code,
            String name, Double stability_rank, Double growth_rank, Double profitability_rank,
            Double activity_rank,
            Double total_rank, Long stock_close) {
        this.corporation_id = corporation_id;
        this.industry_id = industry_id;
        this.industry_type = industry_type;
        this.corporation_code = corporation_code;
        this.name = name;
        this.stability_rank = stability_rank;
        this.growth_rank = growth_rank;
        this.profitability_rank = profitability_rank;
        this.activity_rank = activity_rank;
        this.total_rank = total_rank;
        this.stock_close = stock_close;
    }

    public static CorporationDetailsApiResponse from(ViewCorporationDetailsResponse result){
        return CorporationDetailsApiResponse.builder()
                                            .corporation_id(result.getCorporationId())
                                            .industry_id(result.getIndustryId())
                                            .industry_type(result.getIndustryType())
                                            .corporation_code(result.getCorporationCode())
                                            .name(result.getName())
                                            .stability_rank(result.getStabilityRank())
                                            .growth_rank(result.getGrowthRank())
                                            .profitability_rank(result.getProfitabilityRank())
                                            .activity_rank(result.getActivityRank())
                                            .total_rank(result.getTotalRank())
                                            .stock_close(result.getStockClose())
                                            .build();
    }
}
