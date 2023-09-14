package hasix.junear.billionaire.infra;

import hasix.junear.billionaire.domain.Billionaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBillionaireRepository extends JpaRepository<Billionaire, Long> {

}
