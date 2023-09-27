package hasix.junear.bookmark.domain;

import hasix.junear.bookmark.application.dto.BookmarkInfo;
import hasix.junear.bookmark.application.dto.BookmarkRequest;
import hasix.junear.bookmark.application.dto.BookmarkSearchRequest;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository {
    Bookmark save(Bookmark bookmark);
    Long deleteByMemberIdAndCorporationId(BookmarkRequest bookmarkRequest);
    Optional<Bookmark> findBookmarkByMemberIdAndCorporationId(BookmarkRequest bookmarkRequest);
    List<BookmarkInfo> searchBookmarkByMemberIdAndIndustryIds(BookmarkSearchRequest bookmarkSearchRequest);
}
