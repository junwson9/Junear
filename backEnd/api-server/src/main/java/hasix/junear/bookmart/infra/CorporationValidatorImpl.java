package hasix.junear.bookmart.infra;

import hasix.junear.bookmart.application.CorporationValidator;
import hasix.junear.corporation.domain.Corporation;
import hasix.junear.corporation.infra.mysql.JpaCorporationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CorporationValidatorImpl implements CorporationValidator {

    private final JpaCorporationRepository jpaCorporationRepository;

    @Override
    public boolean validateCorporation(Long corporationId) {
        Optional<Corporation> corporation = jpaCorporationRepository.findById(corporationId);

        return corporation.isPresent();
    }
}
