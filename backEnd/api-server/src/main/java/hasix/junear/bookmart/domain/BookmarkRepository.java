package hasix.junear.bookmart.domain;

import hasix.junear.bookmart.application.BookmarkRequest;

import java.util.Optional;

public interface BookmarkRepository {
    Bookmark save(Bookmark bookmark);
    Long deleteByMemberIdAndCorporationId(BookmarkRequest bookmarkRequest);
    Optional<Bookmark> findBookmarkByMemberIdAndCorporationId(BookmarkRequest bookmarkRequest);
}
