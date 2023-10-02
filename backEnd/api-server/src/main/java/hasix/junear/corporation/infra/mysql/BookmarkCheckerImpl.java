package hasix.junear.corporation.infra.mysql;

import hasix.junear.bookmark.infra.JpaBookmarkRepository;
import hasix.junear.corporation.domain.BookmarkChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkCheckerImpl implements BookmarkChecker {

    private final JpaBookmarkRepository jpaBookmarkRepository;

    @Override
    public boolean existsByCorporationIdAndMemberId(Long corporationId, Long memberId) {
        return jpaBookmarkRepository.existsByCorporationIdAndMemberId(corporationId, memberId);
    }
}
