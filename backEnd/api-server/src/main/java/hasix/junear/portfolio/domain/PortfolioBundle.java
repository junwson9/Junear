package hasix.junear.portfolio.domain;

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
//        private Long stabilityRank;
//        private Long growthRank;
//        private Long profitabilityRank;
//        private Long activityRank;
    //노출 정보_포트폴리오
    private Long stockCount;
    private Long averagePrice;

    @Builder
    public PortfolioBundle(Long corporationId, Long industryId, String industryType,
            String corporationCode, String name, String totalRank, Long stockClose, Long stockCount,
            Long averagePrice) {
        this.corporationId = corporationId;
        this.industryId = industryId;
        this.industryType = industryType;
        this.corporationCode = corporationCode;
        this.name = name;
        this.totalRank = totalRank;
        this.stockClose = stockClose;
        this.stockCount = stockCount;
        this.averagePrice = averagePrice;
    }
}
