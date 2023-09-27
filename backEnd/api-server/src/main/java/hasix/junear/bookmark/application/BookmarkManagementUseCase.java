package hasix.junear.bookmark.application;

import hasix.junear.bookmark.application.dto.BookmarkRequest;
import hasix.junear.bookmark.domain.BookmarkRepository;
import hasix.junear.bookmark.exception.BookmarkException;
import hasix.junear.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkManagementUseCase {

    private final BookmarkRepository bookmarkRepository;
    private final CorporationValidator corporationValidator;

    public void addBookmark(BookmarkRequest bookmarkRequest) {

        if (!corporationValidator.validateCorporation(bookmarkRequest.getCorporationId())) {
            throw new CustomException(BookmarkException.NOT_FOUND_CORPORATION);
        }

        if (isBookmarkExist(bookmarkRequest)) {
            throw new CustomException(BookmarkException.ALREADY_EXIST_BOOKMARK);
        }

        bookmarkRepository.save(bookmarkRequest.toBookmark());
    }

    public void removeBookmark(BookmarkRequest bookmarkRequest) {
        Long removedCount = bookmarkRepository.deleteByMemberIdAndCorporationId(bookmarkRequest);

        if (!isDeleted(removedCount)) {
            throw new CustomException(BookmarkException.NOT_FOUND_BOOKMARK);
        }
    }

    private boolean isDeleted(Long removeCount) {
        return removeCount > 0;
    }

    private boolean isBookmarkExist(BookmarkRequest bookmarkRequest) {
        return bookmarkRepository.findBookmarkByMemberIdAndCorporationId(bookmarkRequest).isPresent();
    }
}
