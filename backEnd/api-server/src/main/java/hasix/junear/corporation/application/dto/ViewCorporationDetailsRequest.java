package hasix.junear.corporation.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewCorporationDetailsRequest {

    private Long id;

    @Builder
    public ViewCorporationDetailsRequest(Long id) {
        this.id = id;
    }

    public static ViewCorporationDetailsRequest from(Long id) {
        return ViewCorporationDetailsRequest.builder()
                .id(id)
                .build();
    }
}
