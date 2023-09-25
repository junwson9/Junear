package hasix.junear.bookmart.application;

import hasix.junear.bookmart.domain.BookmarkRepository;
import hasix.junear.bookmart.exception.BookmarkException;
import hasix.junear.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkManagementUseCase {

    private final BookmarkRepository bookmarkRepository;
    private final CorporationValidator corporationValidator;

    public void addBookmark(BookmarkInfo bookmarkInfo) {
        // 기업 검증
        if (corporationValidator.validateCorporation(bookmarkInfo.getCorporationId())) {
            bookmarkRepository.save(bookmarkInfo.toBookmark());
        } else {
            throw new CustomException(BookmarkException.NOT_FOUND_CORPORATION);
        }
    }

    public void removeBookmark(BookmarkInfo bookmarkInfo) {
        Long removedCount = bookmarkRepository.deleteByMemberIdAndCorporationId(bookmarkInfo);

        if (!isDeleted(removedCount)) {
            throw new CustomException(BookmarkException.PERMISSION_DENIED);
        }
    }

    private boolean isDeleted(Long removeCount) {
        return removeCount > 0;
    }
}
