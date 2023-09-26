package hasix.junear.bookmark.application;

import hasix.junear.bookmark.application.dto.BookmarkInfo;
import hasix.junear.bookmark.application.dto.BookmarkSearchRequest;
import hasix.junear.bookmark.domain.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkSearchUseCase {

    private final BookmarkRepository bookmarkRepository;

    public List<BookmarkInfo> searchBookmark(BookmarkSearchRequest bookmarkSearchRequest) {
        return bookmarkRepository.searchBookmarkByMemberIdAndIndustryIds(bookmarkSearchRequest);
    }

}
