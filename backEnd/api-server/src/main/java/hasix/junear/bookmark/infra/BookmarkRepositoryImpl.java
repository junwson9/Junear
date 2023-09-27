package hasix.junear.bookmark.infra;

import hasix.junear.bookmark.application.dto.BookmarkInfo;
import hasix.junear.bookmark.application.dto.BookmarkRequest;
import hasix.junear.bookmark.application.dto.BookmarkSearchRequest;
import hasix.junear.bookmark.domain.BookmarkRepository;
import hasix.junear.bookmark.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {

    private final JpaBookmarkRepository jpaBookmarkRepository;
    private final QuerydslBookmarkRepository querydslBookmarkRepository;

    @Override
    public Bookmark save(Bookmark bookmark) {
        return jpaBookmarkRepository.save(bookmark);
    }

    @Override
    public Long deleteByMemberIdAndCorporationId(BookmarkRequest bookmarkRequest) {
        return jpaBookmarkRepository.deleteByMemberIdAndCorporationId(bookmarkRequest.getMemberId(), bookmarkRequest.getCorporationId());
    }

    @Override
    public Optional<Bookmark> findBookmarkByMemberIdAndCorporationId(BookmarkRequest bookmarkRequest) {
        return jpaBookmarkRepository.findBookmarkByMemberIdAndCorporationId(bookmarkRequest.getMemberId(), bookmarkRequest.getCorporationId());
    }

    @Override
    public List<BookmarkInfo> searchBookmarkByMemberIdAndIndustryIds(BookmarkSearchRequest bookmarkSearchRequest) {
        return querydslBookmarkRepository.searchBookmarkByMemberIdAndIndustryIds(bookmarkSearchRequest.getMemberId(), bookmarkSearchRequest.getIndustryIds());
    }

}
