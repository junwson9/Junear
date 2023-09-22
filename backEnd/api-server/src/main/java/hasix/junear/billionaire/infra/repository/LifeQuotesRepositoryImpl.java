package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.domain.LifeQuotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LifeQuotesRepositoryImpl implements LifeQuotesRepository {

    private final JpaLifeQuotesRepository jpaLifeQuotesRepository;


    @Override
    public Long countByBillionaireId(Long id) {
        return jpaLifeQuotesRepository.countByBillionaireId(id);
    }
}
