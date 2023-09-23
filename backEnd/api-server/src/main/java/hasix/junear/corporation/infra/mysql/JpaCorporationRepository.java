package hasix.junear.corporation.infra.mysql;

import hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.domain.Corporation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaCorporationRepository extends JpaRepository<Corporation, Long> {

    @Query("SELECT new hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse(c.id, c.industryId, i.type, c.corporationCode, c.name, c.stabilityRank, c.growthRank, c.profitabilityRank, c.activityRank, c.totalRank, c.stockClose) FROM Corporation c LEFT JOIN Industry i ON c.industryId = i.id WHERE c.id = :id")
    Optional<ViewCorporationDetailsResponse> findViewCorporationDetailsResponseById(Long id);

    @Query("SELECT new hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse(c.id, i.type, c.name) FROM Corporation c LEFT JOIN Industry i ON c.industryId = i.id WHERE c.name LIKE CONCAT('%', :keyword, '%')")
    List<SearchCorporationKeywordResponse> findByNameIsContaining(String keyword);

    @Query("SELECT new hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse(c.id, i.type, c.name) FROM Corporation c LEFT JOIN Industry i ON c.industryId = i.id WHERE c.corporationCode LIKE CONCAT('%', :keyword, '%')")
    List<SearchCorporationKeywordResponse> findByCorporationCodeIsContaining(String keyword);

}