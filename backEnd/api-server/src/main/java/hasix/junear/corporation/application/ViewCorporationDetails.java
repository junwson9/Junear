package hasix.junear.corporation.application;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsRequest;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.domain.CorporationRepository;
import hasix.junear.corporation.exception.CorporationErrorCode;
import hasix.junear.corporation.exception.CorporationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewCorporationDetails {

    private final CorporationRepository corporationRepository;

    public ViewCorporationDetailsResponse getCorporationDetails(ViewCorporationDetailsRequest request) {
        return findCorporation(request.getId());
    }

    private ViewCorporationDetailsResponse findCorporation(Long id) {
        return corporationRepository.findViewCorporationDetailsResponseById(id)
                .orElseThrow(() -> new CorporationException(CorporationErrorCode.NOT_FOUND_CORPORATION));
    }
}
