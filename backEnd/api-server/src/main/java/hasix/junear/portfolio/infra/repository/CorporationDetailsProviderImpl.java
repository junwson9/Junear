package hasix.junear.portfolio.infra.repository;

import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.infra.mysql.JpaCorporationRepository;
import hasix.junear.portfolio.domain.CorporationDetailsProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CorporationDetailsProviderImpl implements CorporationDetailsProvider {

    private final JpaCorporationRepository jpaCorporationRepository;

    @Override
    public Optional<ViewCorporationDetailsResponse> findViewCorporationDetailsResponseById(Long id) {
        return jpaCorporationRepository.findViewCorporationDetailsResponseById(id);
    }
}
