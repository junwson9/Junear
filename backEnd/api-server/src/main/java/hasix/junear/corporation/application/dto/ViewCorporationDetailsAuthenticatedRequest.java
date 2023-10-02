package hasix.junear.corporation.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewCorporationDetailsAuthenticatedRequest {

    private Long memberId;
    private Long corporationId;

    @Builder
    public ViewCorporationDetailsAuthenticatedRequest(Long memberId, Long corporationId) {
        this.memberId = memberId;
        this.corporationId = corporationId;
    }

    public static ViewCorporationDetailsAuthenticatedRequest from(Long memberId,
            Long corporationId) {
        return ViewCorporationDetailsAuthenticatedRequest.builder()
                                                         .memberId(memberId)
                                                         .corporationId(corporationId)
                                                         .build();
    }
}