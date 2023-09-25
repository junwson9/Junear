package hasix.junear.bookmart.domain;

import hasix.junear.bookmart.application.BookmarkInfo;

import java.util.Optional;

public interface BookmarkRepository {
    Bookmark save(Bookmark bookmark);
    Long deleteByMemberIdAndCorporationId(BookmarkInfo bookmarkInfo);
}
