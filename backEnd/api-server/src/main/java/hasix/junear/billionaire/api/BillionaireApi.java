package hasix.junear.billionaire.api;

import hasix.junear.billionaire.application.BillionaireLifeQuotesSearchUseCase;
import hasix.junear.common.response.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/billionaire")
public class BillionaireApi {

    private final BillionaireLifeQuotesSearchUseCase billionaireLifeQuotesSearchUseCase;

    @GetMapping("/today")
    public ResponseEntity<?> getTodayBillionairePhrase() {

        // 억만장자 데이터 객체 3개 return

        return ResponseFactory.success("Sample 조회 성공");

    }
}
