package hasix.junear.corporation.api.dto;

import hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CorporationSearchApiResponse {

    private Long corporation_id;
    private Long industry_id;
    private String name;

    @Builder
    public CorporationSearchApiResponse(Long corporation_id, Long industry_id, String name) {
        this.corporation_id = corporation_id;
        this.industry_id = industry_id;
        this.name = name;
    }

    public static List<CorporationSearchApiResponse> from(List<SearchCorporationKeywordResponse> result){
        return result.stream()
                .map(CorporationSearchApiResponse::from)
                .collect(Collectors.toList());
    }

    public static CorporationSearchApiResponse from(SearchCorporationKeywordResponse resource){
        return CorporationSearchApiResponse.builder()
                                           .corporation_id(resource.getCorporationId())
                                           .name(resource.getName())
                                           .industry_id(resource.getIndustryId())
                                           .build();
    }
}
