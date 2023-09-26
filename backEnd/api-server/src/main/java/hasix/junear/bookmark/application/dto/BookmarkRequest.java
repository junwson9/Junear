package hasix.junear.bookmark.application.dto;

import hasix.junear.bookmark.domain.Bookmark;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookmarkRequest {
    Long memberId;
    Long corporationId;

    @Builder
    public BookmarkRequest(Long memberId, Long corporationId) {
        this.memberId = memberId;
        this.corporationId = corporationId;
    }

    public Bookmark toBookmark() {
        return Bookmark.builder()
                .memberId(memberId)
                .corporationId(corporationId)
                .build();
    }
}
