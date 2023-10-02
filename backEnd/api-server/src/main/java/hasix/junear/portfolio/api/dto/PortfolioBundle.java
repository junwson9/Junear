package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.exception.CorporationErrorCode;
import hasix.junear.corporation.exception.CorporationException;
import hasix.junear.portfolio.domain.Portfolio;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class PortfolioBundle {

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
    public PortfolioBundle(Long corporationId, Long industryId, String industryType,
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

}

