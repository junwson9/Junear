package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import java.util.function.ToDoubleFunction;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class MemberBundle {

    //등급
    private String totalPortfolioRank;
    private EachRank eachRank;
    //금액
    private Long totalAssets;
    private List<EachAssets> eachAssets;
    //지표
    private String portfolioStability;
    private String portfolioGrowth;
    private String portfolioProfitability;
    private String portfolioActivity;

    @Builder
    public MemberBundle(String totalPortfolioRank, EachRank eachRank, Long totalAssets,
            List<EachAssets> eachAssets, String portfolioStability, String portfolioGrowth,
            String portfolioProfitability, String portfolioActivity) {
        this.totalPortfolioRank = totalPortfolioRank;
        this.eachRank = eachRank;
        this.totalAssets = totalAssets;
        this.eachAssets = eachAssets;
        this.portfolioStability = portfolioStability;
        this.portfolioGrowth = portfolioGrowth;
        this.portfolioProfitability = portfolioProfitability;
        this.portfolioActivity = portfolioActivity;
    }

}
