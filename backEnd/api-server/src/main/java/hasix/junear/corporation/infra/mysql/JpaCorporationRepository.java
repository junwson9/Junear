package hasix.junear.corporation.infra.mysql;

import hasix.junear.corporation.domain.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCorporationRepository extends JpaRepository<Corporation, Long> {

}
