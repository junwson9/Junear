package hasix.junear.corporation.infra.mysql;

import hasix.junear.corporation.domain.Corporation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCorporationRepository extends JpaRepository<Corporation, Long> {

    List<Corporation> findByNameIsContaining(String keyword);

    List<Corporation> findByCorporationCodeIsContaining(Long keyword);

}
