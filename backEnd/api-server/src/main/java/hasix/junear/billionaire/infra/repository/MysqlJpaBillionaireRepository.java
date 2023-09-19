package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.application.dto.TodayLifeQuotes;
import hasix.junear.billionaire.domain.Billionaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MysqlJpaBillionaireRepository extends JpaRepository<Billionaire, Long> {

    // JPQL join 쿼리
    @Query("SELECT NEW hasix.junear.billionaire.application.dto.TodayLifeQuotes(b.name, b.imageUrl, p.phrase) FROM Billionaire b JOIN LifeQuotes p ON b.id = p.billionaireId WHERE p.id IN :ids")
    List<TodayLifeQuotes> findBillionaireLifeQuotesByIds(@Param("ids") List<Long> ids);
}
