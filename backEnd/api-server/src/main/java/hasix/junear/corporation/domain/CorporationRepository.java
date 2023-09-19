package hasix.junear.corporation.domain;

import java.util.Optional;

public interface CorporationRepository {

    Optional<Corporation> findById(Long id);

}
