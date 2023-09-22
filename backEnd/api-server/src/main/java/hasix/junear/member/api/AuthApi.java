package hasix.junear.member.api;


import hasix.junear.common.response.ResponseFactory;
import hasix.junear.member.api.dto.OauthLoginApiRequest;
import hasix.junear.member.api.dto.OauthLoginApiResponse;
import hasix.junear.member.api.dto.TokenReissueApiRequest;
import hasix.junear.member.api.dto.TokenReissueApiResponse;
import hasix.junear.member.application.LogoutUseCase;
import hasix.junear.member.application.OauthLoginUseCase;
import hasix.junear.member.application.TokenReissueUseCase;
import hasix.junear.member.application.dto.OauthLoginResponse;
import hasix.junear.member.application.dto.TokenReissueRequest;
import hasix.junear.member.application.dto.TokenReissueResponse;
import hasix.junear.springconfig.config.auth.AuthMember;
import hasix.junear.springconfig.config.auth.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApi {

    private final LogoutUseCase logoutUseCase;
    private final OauthLoginUseCase oauthLoginUseCase;
    private final TokenReissueUseCase tokenReissueUseCase;

    @GetMapping("/api/member/logout")
    public ResponseEntity<?> logout(@AuthenticatedMember AuthMember member ){
        logoutUseCase.logout(member.getId());
        return ResponseFactory.success("로그아웃 성공");
    }

    @PostMapping("/api/member/oauth")
    public ResponseEntity<?> oauthLogin(@Validated @RequestBody OauthLoginApiRequest request) {
        OauthLoginResponse result = oauthLoginUseCase.login(request.toApplicationDto());
        return ResponseFactory.success("로그인 성공", OauthLoginApiResponse.from(result));
    }

    @PostMapping("/api/member/reissue")
    public ResponseEntity<?> reissueToken(@AuthenticatedMember AuthMember member,
            @Validated @RequestBody TokenReissueApiRequest request) {
        TokenReissueResponse result = tokenReissueUseCase.reissue(
                new TokenReissueRequest(member.getId(), request.getRefreshToken()));
        return ResponseFactory.success("재발급 성공", new TokenReissueApiResponse(result.getAccessToken()
                                                                                   .getToken()));
    }

}
