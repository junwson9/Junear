package hasix.junear.corporation.application.dto;

import hasix.junear.corporation.domain.Corporation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchCorporationKeywordResponse {
    private Long corporationId;
    private Long industryId;
    private String name;

    @Builder
    public SearchCorporationKeywordResponse(Long corporationId, Long industryId, String name) {
        this.corporationId = corporationId;
        this.industryId = industryId;
        this.name = name;
    }

    public static List<SearchCorporationKeywordResponse> from(List<Corporation> corporationList) {
        if(corporationList == null) return null;
        return corporationList.stream()
                              .map(corporation -> SearchCorporationKeywordResponse.from(corporation))
                .collect(Collectors.toList());
    }

    private static SearchCorporationKeywordResponse from(Corporation corporation) {
        return SearchCorporationKeywordResponse.builder()
                .corporationId(corporation.getId())
                .name(corporation.getName())
                .industryId(corporation.getIndustryId())
                .build();
    }
}
