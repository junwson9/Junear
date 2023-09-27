package hasix.junear.bookmark.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hasix.junear.bookmark.application.dto.BookmarkInfo;
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
    private String industryType;

    @Builder
    public BookmarkSearchApiResponse(Long corporationId, String corporationName, String industryType) {
        this.corporationId = corporationId;
        this.corporationName = corporationName;
        this.industryType = industryType;
    }

    public static BookmarkSearchApiResponse from(BookmarkInfo bookmarkInfo) {
        return BookmarkSearchApiResponse.builder()
                .corporationId(bookmarkInfo.getCorporationId())
                .corporationName(bookmarkInfo.getCorporationName())
                .industryType(bookmarkInfo.getIndustry())
                .build();
    }
}
