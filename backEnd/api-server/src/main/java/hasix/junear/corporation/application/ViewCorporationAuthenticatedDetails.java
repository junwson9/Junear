package hasix.junear.corporation.application;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsAuthenticatedRequest;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsAuthenticatedResponse;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.domain.BookmarkChecker;
import hasix.junear.corporation.domain.CorporationRepository;
import hasix.junear.corporation.exception.CorporationErrorCode;
import hasix.junear.corporation.exception.CorporationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewCorporationAuthenticatedDetails {

    private final CorporationRepository corporationRepository;
    private final BookmarkChecker bookmarkChecker;

    public ViewCorporationDetailsAuthenticatedResponse getCorporationDetails(
            ViewCorporationDetailsAuthenticatedRequest request) {

        boolean isBookmarkedResult = checkBookmarkExist(request.getCorporationId(), request.getMemberId());
        ViewCorporationDetailsResponse findCorporationResult = findCorporation(request.getCorporationId());
        return ViewCorporationDetailsAuthenticatedResponse.from(isBookmarkedResult, findCorporationResult);
    }

    private boolean checkBookmarkExist(Long corporationId, Long memberId) {

        return bookmarkChecker.existsByCorporationIdAndMemberId(corporationId, memberId);
    }

    private ViewCorporationDetailsResponse findCorporation(Long corporationId) {
        return corporationRepository.findViewCorporationDetailsResponseById(corporationId)
                .orElseThrow(() -> new CorporationException(CorporationErrorCode.NOT_FOUND_CORPORATION));
    }
}
