package hasix.junear.bookmart.infra;

import hasix.junear.bookmart.application.BookmarkRequest;
import hasix.junear.bookmart.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBookmarkRepository extends JpaRepository<Bookmark, Long> {
    Long deleteByMemberIdAndCorporationId(Long memberId, Long corporationId);
    Optional<Bookmark> findBookmarkByMemberIdAndCorporationId(Long memberId, Long corporationId);
}
