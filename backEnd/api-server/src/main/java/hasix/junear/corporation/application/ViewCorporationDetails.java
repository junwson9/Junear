package hasix.junear.corporation.application;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.domain.Corporation;
import hasix.junear.corporation.domain.CorporationRepository;
import hasix.junear.corporation.exception.CorporationErrorCode;
import hasix.junear.corporation.exception.CorporationException;
import hasix.junear.member.application.dto.SampleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewCorporationDetails {

    private final CorporationRepository corporationRepository;

    public ViewCorporationDetailsResponse getCorporationDetails(Long id) {
        Corporation corporation = findCorporation(id);
        return ViewCorporationDetailsResponse.from(corporation);
    }

    private Corporation findCorporation(Long id) {
        return corporationRepository.findById(id)
                .orElseThrow(() -> new CorporationException(CorporationErrorCode.NOT_FOUND_CORPORATION));
    }
}
