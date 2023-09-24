package hasix.junear.bookmart.application;

import hasix.junear.bookmart.domain.Bookmark;
import hasix.junear.bookmart.domain.BookmarkRepository;
import hasix.junear.bookmart.exception.BookmarkException;
import hasix.junear.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkManagementUseCase {

    private final BookmarkRepository bookmarkRepository;

    public void addBookmark(BookmarkInfo bookmarkInfo) {
        Bookmark bookmark = bookmarkRepository.save(bookmarkInfo.toBookmark());

        if (!isAdded(bookmark)) {
            throw new CustomException(BookmarkException.NOT_FOUND_BOOKMARK);
        }
    }

    public void removeBookmark(BookmarkInfo bookmarkInfo) {
        Long removedCount = bookmarkRepository.deleteByMemberIdAndCorporationId(bookmarkInfo);

        if (!isDeleted(removedCount)) {
            throw new CustomException(BookmarkException.PERMISSION_DENIED);
        }
    }

    private boolean isAdded(Bookmark bookmark) {
        return bookmark != null;
    }

    private boolean isDeleted(Long removeCount) {
        return removeCount > 0;
    }
}
