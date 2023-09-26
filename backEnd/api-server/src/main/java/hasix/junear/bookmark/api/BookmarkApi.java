package hasix.junear.bookmark.api;

import hasix.junear.bookmark.api.dto.BookmarkSearchApiResponse;
import hasix.junear.bookmark.application.BookmarkManagementUseCase;
import hasix.junear.bookmark.application.BookmarkSearchUseCase;
import hasix.junear.bookmark.application.dto.BookmarkInfo;
import hasix.junear.bookmark.application.dto.BookmarkRequest;
import hasix.junear.bookmark.application.dto.BookmarkSearchRequest;
import hasix.junear.common.response.ResponseFactory;
import hasix.junear.springconfig.config.auth.AuthMember;
import hasix.junear.springconfig.config.auth.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookmark")
@RequiredArgsConstructor
public class BookmarkApi {

    private final BookmarkManagementUseCase bookmarkManagementUseCase;
    private final BookmarkSearchUseCase bookmarkSearchUseCase;

    @PostMapping("/{corporation_id}")
    public ResponseEntity<?> addBookmark(@PathVariable("corporation_id") Long corporationId, @AuthenticatedMember AuthMember authMember) {

        bookmarkManagementUseCase.addBookmark(new BookmarkRequest(authMember.getId(), corporationId));

        return ResponseFactory.success("북마크 등록 성공");
    }

    @DeleteMapping("/{corporation_id}")
    public ResponseEntity<?> removeBookmark(@PathVariable("corporation_id") Long corporationId, @AuthenticatedMember AuthMember authMember) {

        bookmarkManagementUseCase.removeBookmark(new BookmarkRequest(authMember.getId(), corporationId));

        return ResponseFactory.success("북마크 삭제 성공");
    }

    @GetMapping
//    public ResponseEntity<?> getBookmark(@RequestParam(value = "industry_id", required = false) List<Long> industryIds, @AuthenticatedMember AuthMember authMember) {
    public ResponseEntity<?> getBookmark(@RequestParam(value = "industry_id", required = false) List<Long> industryIds) {

//        List<BookmarkInfo> bookmarkInfoList = bookmarkSearchUseCase.searchBookmark(BookmarkSearchRequest.to(authMember.getId(), industryIdList));
        List<BookmarkInfo> bookmarkInfoList = bookmarkSearchUseCase.searchBookmark(BookmarkSearchRequest.to(1L, industryIds));

        List<BookmarkSearchApiResponse> bookmarkSearchApiResponseList = bookmarkInfoList.stream()
                .map(BookmarkSearchApiResponse::from)
                .collect(Collectors.toList());

        return ResponseFactory.success("북마크 조회 성공", bookmarkSearchApiResponseList);
    }
}
