package hasix.junear.corporation.infra.mysql;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.domain.Corporation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaCorporationRepository extends JpaRepository<Corporation, Long> {

    @Query("SELECT new hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse(c.id, c.industryId, i.type, c.corporationCode, c.name, c.stabilityRank, c.growthRank, c.profitabilityRank, c.activityRank, c.totalRank, c.stockClose) FROM Corporation c LEFT JOIN Industry i ON c.industryId = i.id WHERE c.id = :id")
    Optional<ViewCorporationDetailsResponse> findViewCorporationDetailsResponseById(Long id);

    List<Corporation> findByNameIsContaining(String keyword);

    List<Corporation> findByCorporationCodeIsContaining(String keyword);

}