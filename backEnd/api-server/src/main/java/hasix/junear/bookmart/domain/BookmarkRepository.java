package hasix.junear.bookmart.domain;

import hasix.junear.bookmart.application.BookmarkRequest;

public interface BookmarkRepository {
    Bookmark save(Bookmark bookmark);
    Long deleteByMemberIdAndCorporationId(BookmarkRequest bookmarkRequest);
}
