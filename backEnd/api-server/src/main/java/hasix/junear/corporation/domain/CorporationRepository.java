package hasix.junear.corporation.domain;

import java.util.List;
import java.util.Optional;

public interface CorporationRepository {

    Optional<Corporation> findById(Long id);

    List<Corporation> findByNameIsContaining(String keyword);

    List<Corporation> findByIndustryIdIsContaining(String keyword);

}
