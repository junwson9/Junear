package hasix.junear.corporation.api.dto;

import hasix.junear.corporation.application.ViewCorporationDetails;
import hasix.junear.corporation.application.dto.SearchCorporationResponse;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import lombok.Builder;

public class CorporationApiResponse {

    @Builder
    public CorporationApiResponse() {
    }

    public static CorporationApiResponse from(ViewCorporationDetailsResponse result){
        return CorporationApiResponse.builder()
                                     .build();
    }

    public static CorporationApiResponse from(SearchCorporationResponse result){
        return CorporationApiResponse.builder()
                                .build();
    }

}
