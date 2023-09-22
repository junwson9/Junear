package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.application.dto.TodayLifeQuotes;
import hasix.junear.billionaire.domain.Billionaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaBillionaireRepository extends JpaRepository<Billionaire, Long> {

    // JPQL join 쿼리
    @Query(value = "SELECT b.name, b.image_url, l.phrase FROM billionaire b JOIN life_quotes l ON b.billionaire_id = l.billionaire_id WHERE l.billionaire_id IN :ids", nativeQuery = true)
    List<Object> findBillionaireLifeQuotesByIds(@Param("ids") List<Long> ids);
}
