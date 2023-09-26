package hasix.junear.bookmark.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hasix.junear.bookmark.application.dto.BookmarkInfo;
import hasix.junear.news.application.dto.IndustryNewsResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class BookmarkSearchApiResponse {

    private Long corporationId;
    private String corporationName;
    private String industry;

    @Builder
    public BookmarkSearchApiResponse(Long corporationId, String corporationName, String industry) {
        this.corporationId = corporationId;
        this.corporationName = corporationName;
        this.industry = industry;
    }

    public static BookmarkSearchApiResponse from(BookmarkInfo bookmarkInfo) {
        return BookmarkSearchApiResponse.builder()
                .corporationId(bookmarkInfo.getCorporationId())
                .corporationName(bookmarkInfo.getCorporationName())
                .industry(bookmarkInfo.getIndustry())
                .build();
    }
}
