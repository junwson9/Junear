package hasix.junear.billionaire.application;

import hasix.junear.billionaire.application.dto.BillionaireLifeQuotesResponse;
import hasix.junear.billionaire.domain.BillionaireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BillionaireLifeQuotesSearchUseCase {

    // 억만장자 데이터 조회
    private final BillionaireRepository billionaireRepository;
    private final String phraseKey = "today_phrase";

    public List<BillionaireLifeQuotesResponse> search() {
        log.info("억만장자 데이터 조회 시작");
        return billionaireRepository.findBillionaireLifeQuotes(phraseKey);
    }

}
