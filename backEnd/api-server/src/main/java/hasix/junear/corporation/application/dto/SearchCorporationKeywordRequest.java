package hasix.junear.corporation.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchCorporationKeywordRequest {

    private String keyword;

    @Builder
    public SearchCorporationKeywordRequest(String keyword) {
        this.keyword = keyword;
    }

    public static SearchCorporationKeywordRequest from(String keyword) {
        return SearchCorporationKeywordRequest.builder()
                .keyword(keyword)
                .build();
    }

}
