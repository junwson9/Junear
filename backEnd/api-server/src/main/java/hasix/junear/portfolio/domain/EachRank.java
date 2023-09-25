package hasix.junear.portfolio.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EachRank {

    private Long s;
    private Long aPlus;
    private Long a;
    private Long bPlus;
    private Long b;
    private Long cPlus;
    private Long c;

    @Builder
    public EachRank(Long s, Long aPlus, Long a, Long bPlus, Long b, Long cPlus, Long c) {
        this.s = s;
        this.aPlus = aPlus;
        this.a = a;
        this.bPlus = bPlus;
        this.b = b;
        this.cPlus = cPlus;
        this.c = c;
    }
}
