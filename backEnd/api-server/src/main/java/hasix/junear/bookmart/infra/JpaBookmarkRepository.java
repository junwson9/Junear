package hasix.junear.bookmart.infra;

import hasix.junear.bookmart.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookmarkRepository extends JpaRepository<Bookmark, Long> {
    Long deleteByMemberIdAndCorporationId(Long memberId, Long corporationId);
}
