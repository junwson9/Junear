package hasix.junear.portfolio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "company_id", nullable = false)
    private Long corporationId;

    @Setter
    @Column(name = "stock_count", nullable = false)
    private Long stockCount;

    @Setter
    @Column(name = "average_price", nullable = false)
    private Long averagePrice;

    @Builder
    public Portfolio(Long id, Long memberId, Long corporationId, Long stockCount, Long averagePrice) {
        this.id = id;
        this.memberId = memberId;
        this.corporationId = corporationId;
        this.stockCount = stockCount;
        this.averagePrice = averagePrice;
    }
}
