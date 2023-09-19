package hasix.junear.billionaire.domain;

import hasix.junear.billionaire.application.dto.BillionaireLifeQuotesResponse;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BillionaireRepository {
    List<BillionaireLifeQuotesResponse> findBillionaireLifeQuotes(String key);
}
