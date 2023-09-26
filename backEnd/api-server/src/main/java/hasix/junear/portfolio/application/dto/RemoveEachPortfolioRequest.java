package hasix.junear.portfolio.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveEachPortfolioRequest {

    private Long memberId;
    private Long corporationId;

    @Builder
    public RemoveEachPortfolioRequest(Long memberId, Long corporationId) {
        this.memberId = memberId;
        this.corporationId = corporationId;
    }

    public static RemoveEachPortfolioRequest from(Long memberId, Long corporationId) {

        return RemoveEachPortfolioRequest.builder()
                                         .memberId(memberId)
                                         .corporationId(corporationId)
                                         .build();
    }
}
