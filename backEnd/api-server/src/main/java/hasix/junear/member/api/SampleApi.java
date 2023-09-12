package hasix.junear.member.api;


import hasix.junear.common.response.ResponseFactory;
import hasix.junear.member.api.dto.SampleApiRequest;
import hasix.junear.member.api.dto.SampleApiResponse;
import hasix.junear.member.application.SampleUseCase;
import hasix.junear.member.application.dto.SampleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleApi {

    private final SampleUseCase sampleUseCase;

    @GetMapping("/sampleUser")
    public ResponseEntity<?> getSample(@Validated SampleApiRequest apiRequest) {
        SampleResponse result = sampleUseCase.getSample(apiRequest.to());
        return ResponseFactory.success("Sample 조회 성공", SampleApiResponse.from(result));
    }
}
