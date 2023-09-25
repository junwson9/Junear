package hasix.junear.portfolio.domain;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import java.util.Optional;

public interface CorporationDetailsProvider {

    Optional<ViewCorporationDetailsResponse> findViewCorporationDetailsResponseById(Long id);

}
