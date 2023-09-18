package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.application.dto.BillionaireLifeQuotesResponse;
import hasix.junear.billionaire.domain.Billionaire;
import hasix.junear.billionaire.domain.BillionaireRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MysqlJpaBillionaireRepository extends JpaRepository<Billionaire, Long> {


    // JPQL join 쿼리


}
