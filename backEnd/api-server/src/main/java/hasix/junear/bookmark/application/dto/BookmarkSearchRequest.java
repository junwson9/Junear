package hasix.junear.bookmark.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookmarkSearchRequest {
    Long memberId;
    List<Long> industryIds;

    @Builder
    public BookmarkSearchRequest(Long memberId, List<Long> industryIds) {
        this.memberId = memberId;
        this.industryIds = industryIds;
    }

    public static BookmarkSearchRequest to(Long memberId, List<Long> industryIds) {
        return BookmarkSearchRequest.builder()
                .memberId(memberId)
                .industryIds(industryIds)
                .build();
    }
}
