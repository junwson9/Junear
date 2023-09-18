package hasix.junear.billionaire.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BillionaireLifeQuotesSearchUseCase {

    // 억만장자 데이터 조회

    // null이면 mysql에서 새롭게 데이터하는 로직으로 이동

    public void search() {

    }

}
