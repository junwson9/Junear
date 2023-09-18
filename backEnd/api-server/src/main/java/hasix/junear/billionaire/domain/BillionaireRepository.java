package hasix.junear.billionaire.domain;

import hasix.junear.billionaire.application.dto.BillionaireLifeQuotesResponse;

import java.util.List;

public interface BillionaireRepository {
    List<BillionaireLifeQuotesResponse> findById(List<Long> idList);
}
