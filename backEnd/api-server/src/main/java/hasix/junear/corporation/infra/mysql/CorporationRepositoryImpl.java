package hasix.junear.corporation.infra.mysql;

import hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.domain.Corporation;
import hasix.junear.corporation.domain.CorporationRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CorporationRepositoryImpl implements CorporationRepository {

    private final JpaCorporationRepository jpaCorporationRepository;

    @Override
    public Optional<ViewCorporationDetailsResponse> findViewCorporationDetailsResponseById(Long id) {
        return jpaCorporationRepository.findViewCorporationDetailsResponseById(id);
    }

    @Override
    public List<SearchCorporationKeywordResponse> findByNameIsContaining(String keyword) {
        return jpaCorporationRepository.findByNameIsContaining(keyword);
    }

    @Override
    public List<SearchCorporationKeywordResponse> findByCorporationCodeIsContaining(String keyword) {
        return jpaCorporationRepository.findByCorporationCodeIsContaining(keyword);
    }
}
