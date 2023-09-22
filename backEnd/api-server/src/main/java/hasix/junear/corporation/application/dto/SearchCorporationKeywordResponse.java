package hasix.junear.corporation.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchCorporationKeywordResponse {
    private Long corporationId;
    private String industryType;
    private String name;

    @Builder
    public SearchCorporationKeywordResponse(Long corporationId, String industryType, String name) {
        this.corporationId = corporationId;
        this.industryType = industryType;
        this.name = name;
    }
}
