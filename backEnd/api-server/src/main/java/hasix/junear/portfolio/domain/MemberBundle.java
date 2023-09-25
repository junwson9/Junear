package hasix.junear.portfolio.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberBundle {

    //등급
    private Long totalPortfolioRank;
    private EachRank eachRank;
    //금액
    private Long totalAssets;
    private EachAssets eachAssets;
    //지표
    private String portfolioStability;
    private String portfolioGrowth;
    private String portfolioProfitability;
    private String portfolioActivity;

    @Builder
    public MemberBundle(Long totalPortfolioRank, EachRank eachRank, Long totalAssets,
            EachAssets eachAssets, String portfolioStability, String portfolioGrowth,
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
