package hasix.junear.bookmart.infra;

import hasix.junear.bookmart.application.BookmarkRequest;
import hasix.junear.bookmart.domain.Bookmark;
import hasix.junear.bookmart.domain.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {

    private final JpaBookmarkRepository jpaBookmarkRepository;

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

}
