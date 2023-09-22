package hasix.junear.corporation.api.dto;

import hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CorporationSearchApiResponse {

    private Long corporationId;
    private Long industryId;
    private String name;

    @Builder
    public CorporationSearchApiResponse(Long corporationId, Long industryId, String name) {
        this.corporationId = corporationId;
        this.industryId = industryId;
        this.name = name;
    }

    public static List<CorporationSearchApiResponse> from(List<SearchCorporationKeywordResponse> result){
        return result.stream()
                .map(CorporationSearchApiResponse::from)
                .collect(Collectors.toList());
    }

    public static CorporationSearchApiResponse from(SearchCorporationKeywordResponse resource){
        return CorporationSearchApiResponse.builder()
                                           .corporationId(resource.getCorporationId())
                                           .name(resource.getName())
                                           .industryId(resource.getIndustryId())
                                           .build();
    }
}
