package hasix.junear.bookmart.api;

import hasix.junear.bookmart.application.BookmarkInfo;
import hasix.junear.bookmart.application.BookmarkManagementUseCase;
import hasix.junear.common.response.ResponseFactory;
import hasix.junear.springconfig.config.auth.AuthMember;
import hasix.junear.springconfig.config.auth.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmark")
@RequiredArgsConstructor
public class BookmarkApi {

    private final BookmarkManagementUseCase bookmarkManagementUseCase;

    @PostMapping("/{corporation_id}")
    public ResponseEntity<?> addBookmark(@PathVariable("corporation_id") Long corporationId, @AuthenticatedMember AuthMember authMember) {

        bookmarkManagementUseCase.addBookmark(new BookmarkInfo(authMember.getId(), corporationId));

        return ResponseFactory.success("북마크 등록 성공");
    }

    @DeleteMapping("/{corporation_id}")
    public ResponseEntity<?> removeBookmark(@PathVariable("corporation_id") Long corporationId, @AuthenticatedMember AuthMember authMember) {

        bookmarkManagementUseCase.removeBookmark(new BookmarkInfo(authMember.getId(), corporationId));

        return ResponseFactory.success("북마크 삭제 성공");
    }
}
