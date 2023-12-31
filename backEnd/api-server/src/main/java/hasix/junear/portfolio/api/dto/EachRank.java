package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class EachRank {

    private Long s;
    private Long aPlus;
    private Long a;
    private Long bPlus;
    private Long b;
    private Long cPlus;
    private Long c;
    private Long totalCount;

    @Builder
    public EachRank(Long s, Long aPlus, Long a, Long bPlus, Long b, Long cPlus, Long c, Long totalCount) {
        this.s = s;
        this.aPlus = aPlus;
        this.a = a;
        this.bPlus = bPlus;
        this.b = b;
        this.cPlus = cPlus;
        this.c = c;
        this.totalCount = totalCount;
    }
}
