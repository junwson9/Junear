package hasix.junear.corporation.application;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.member.application.dto.SampleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewCorporationDetails {

    public ViewCorporationDetailsResponse getCorporationDetails(SampleRequest sampleRequest) {
        return null;
    }
}
