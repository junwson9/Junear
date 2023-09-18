package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.application.dto.BillionaireLifeQuotesResponse;
import hasix.junear.billionaire.domain.BillionaireRepository;

import java.util.List;

public class BillionaireRepositoryImpl implements BillionaireRepository {
    @Override
    public List<BillionaireLifeQuotesResponse> findById(List<Long> idList) {

        // 어떤 로직으로 데이터를 조회할 것이가


        return null;
    }
}
