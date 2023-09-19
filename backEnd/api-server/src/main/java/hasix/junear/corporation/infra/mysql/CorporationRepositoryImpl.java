package hasix.junear.corporation.infra.mysql;

import hasix.junear.corporation.domain.Corporation;
import hasix.junear.corporation.domain.CorporationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CorporationRepositoryImpl implements CorporationRepository {

    private final JpaCorporationRepository jpaCorporationRepository;

    @Override
    public Optional<Corporation> findById(Long id) {
        return jpaCorporationRepository.findById(id);
    }
}
