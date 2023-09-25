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

    public void addBookmark(BookmarkRequest bookmarkRequest) {
        // 기업 검증
        if (corporationValidator.validateCorporation(bookmarkRequest.getCorporationId())) {
            bookmarkRepository.save(bookmarkRequest.toBookmark());
        } else {
            throw new CustomException(BookmarkException.NOT_FOUND_CORPORATION);
        }
    }

    public void removeBookmark(BookmarkRequest bookmarkRequest) {
        Long removedCount = bookmarkRepository.deleteByMemberIdAndCorporationId(bookmarkRequest);

        if (!isDeleted(removedCount)) {
            throw new CustomException(BookmarkException.PERMISSION_DENIED);
        }
    }

    private boolean isDeleted(Long removeCount) {
        return removeCount > 0;
    }
}
