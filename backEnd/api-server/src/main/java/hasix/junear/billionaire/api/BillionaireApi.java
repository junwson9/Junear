package hasix.junear.billionaire.api;

import hasix.junear.billionaire.application.BillionairePhraseUseCase;
import hasix.junear.common.response.ResponseFactory;
import hasix.junear.member.api.dto.SampleApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/billionaire")
public class BillionaireApi {

    private final BillionairePhraseUseCase billionairePhraseUseCase;

    @GetMapping("/today")
    public ResponseEntity<?> getTodayBillionairePhrase() {

        return ResponseFactory.success("Sample 조회 성공");

    }
}
