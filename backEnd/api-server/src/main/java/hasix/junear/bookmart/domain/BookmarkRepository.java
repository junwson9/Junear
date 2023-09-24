package hasix.junear.bookmart.domain;

import hasix.junear.bookmart.application.BookmarkInfo;

public interface BookmarkRepository {
    Bookmark save(Bookmark bookmark);
    Long deleteByMemberIdAndCorporationId(BookmarkInfo bookmarkInfo);
}
