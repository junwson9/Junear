package hasix.junear.member.api;


import hasix.junear.common.response.ResponseFactory;
import hasix.junear.member.api.dto.OauthLoginApiRequest;
import hasix.junear.member.application.OauthLoginUseCase;
import hasix.junear.member.application.dto.OauthLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthApi {
    private final OauthLoginUseCase oauthLoginUseCase;

    @PostMapping("/api/member/oauth")
    public ResponseEntity<?> oauthLogin(@RequestBody OauthLoginApiRequest request){
        OauthLoginResponse result = oauthLoginUseCase.login(request.toApplicationDto());
        return ResponseFactory.success("로그인 성공", result);
    }
}
