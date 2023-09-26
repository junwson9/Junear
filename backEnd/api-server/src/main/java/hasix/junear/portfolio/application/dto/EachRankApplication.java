package hasix.junear.portfolio.application.dto;

import hasix.junear.portfolio.api.dto.EachRank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EachRankApplication {

    private Long s;
    private Long aPlus;
    private Long a;
    private Long bPlus;
    private Long b;
    private Long cPlus;
    private Long c;
    private Long totalCount;

    @Builder
    public EachRankApplication(Long s, Long aPlus, Long a, Long bPlus, Long b, Long cPlus, Long c,
            Long totalCount) {
        this.s = s;
        this.aPlus = aPlus;
        this.a = a;
        this.bPlus = bPlus;
        this.b = b;
        this.cPlus = cPlus;
        this.c = c;
        this.totalCount = totalCount;
    }

    public static EachRank toEachRank(EachRankApplication eachRankApplication) {
        return EachRank.builder()
                       .s(eachRankApplication.getS())
                       .aPlus(eachRankApplication.getAPlus())
                       .a(eachRankApplication.getA())
                       .bPlus(eachRankApplication.getBPlus())
                       .b(eachRankApplication.getB())
                       .cPlus(eachRankApplication.getCPlus())
                       .c(eachRankApplication.getC())
                       .build();
    }
}
