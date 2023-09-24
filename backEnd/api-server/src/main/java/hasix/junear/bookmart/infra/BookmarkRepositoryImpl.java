package hasix.junear.bookmart.infra;

import hasix.junear.bookmart.application.BookmarkInfo;
import hasix.junear.bookmart.domain.Bookmark;
import hasix.junear.bookmart.domain.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {

    private final JpaBookmarkRepository jpaBookmarkRepository;

    @Override
    public Bookmark save(Bookmark bookmark) {
        return jpaBookmarkRepository.save(bookmark);
    }

    @Override
    public Long deleteByMemberIdAndCorporationId(BookmarkInfo bookmarkInfo) {
        return jpaBookmarkRepository.deleteByMemberIdAndCorporationId(bookmarkInfo.getMemberId(), bookmarkInfo.getCorporationId());
    }
}
