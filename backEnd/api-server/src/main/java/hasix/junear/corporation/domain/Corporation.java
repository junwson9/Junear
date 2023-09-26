package hasix.junear.corporation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Corporation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "corporation_id")
    private Long id;

    @Column(name = "industry_id")
    private Long industryId;

    @Column(name = "corporation_code")
    private String corporationCode;

    @Column
    private String name;

    @Column(name = "stability_rank")
    private Double stabilityRank;

    @Column(name = "growth_rank")
    private Double growthRank;

    @Column(name = "profitability_rank")
    private Double profitabilityRank;

    @Column(name = "activity_rank")
    private Double activityRank;

    @Column(name = "total_rank")
    private Double totalRank;

    @Column(name = "stock_close")
    private Long stockClose;

}
