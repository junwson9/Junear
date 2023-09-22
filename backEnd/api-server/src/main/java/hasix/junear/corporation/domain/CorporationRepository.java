package hasix.junear.corporation.domain;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import java.util.List;
import java.util.Optional;

public interface CorporationRepository {

    Optional<ViewCorporationDetailsResponse> findViewCorporationDetailsResponseById(Long id);

    List<Corporation> findByNameIsContaining(String keyword);

    List<Corporation> findByCorporationCodeIsContaining(String keyword);

}
