package hasix.junear.billionaire.api;

import hasix.junear.billionaire.application.BillionaireLifeQuotesSearchUseCase;
import hasix.junear.billionaire.application.dto.BillionaireLifeQuotesResponse;
import hasix.junear.common.response.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/billionaire")
public class BillionaireApi {

    private final BillionaireLifeQuotesSearchUseCase billionaireLifeQuotesSearchUseCase;

    @GetMapping("/today")
    public ResponseEntity<?> getTodayBillionairePhrase() {

        List<BillionaireLifeQuotesResponse> search = billionaireLifeQuotesSearchUseCase.search();

        return ResponseFactory.success("억만장자 조회 성공", search);

    }
}
