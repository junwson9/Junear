package hasix.junear.member.api;


import hasix.junear.common.exception.CommonErrorCode;
import hasix.junear.common.exception.CustomException;
import hasix.junear.common.response.ResponseFactory;
import hasix.junear.member.api.dto.OauthLoginApiRequest;
import hasix.junear.member.api.dto.OauthLoginApiResponse;
import hasix.junear.member.api.dto.TokenReissueApiResponse;
import hasix.junear.member.application.LogoutUseCase;
import hasix.junear.member.application.OauthLoginUseCase;
import hasix.junear.member.application.TokenReissueUseCase;
import hasix.junear.member.application.dto.OauthLoginResponse;
import hasix.junear.member.application.dto.TokenReissueRequest;
import hasix.junear.member.application.dto.TokenReissueResponse;
import hasix.junear.member.domain.Token;
import hasix.junear.springconfig.config.auth.AuthMember;
import hasix.junear.springconfig.config.auth.AuthenticatedMember;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
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
    private final String REFRESH_COOKIE_KEY = "refresh-cookie";
    private final Environment environment;

    @GetMapping("/api/member/logout")
    public ResponseEntity<?> logout(@AuthenticatedMember AuthMember member, HttpServletResponse response){
        logoutUseCase.logout(member.getId());
        Cookie removedCooke = createRefreshCookie(null);
        removedCooke.setMaxAge(0);
        response.addCookie(removedCooke);
        return ResponseFactory.success("로그아웃 성공");
    }

    @PostMapping("/api/member/oauth")
    public ResponseEntity<?> oauthLogin(@Validated @RequestBody OauthLoginApiRequest request, HttpServletResponse response){
        OauthLoginResponse result = oauthLoginUseCase.login(request.toApplicationDto());
        Token accessToken = result.getAccessToken();
        Token refreshToken = result.getRefreshToken();
        response.addCookie(createRefreshCookie(refreshToken.getToken()));
        return ResponseFactory.success("로그인 성공", new OauthLoginApiResponse(accessToken.getToken()));
    }

    @PostMapping("/api/member/reissue")
    public ResponseEntity<?> reissueToken(@AuthenticatedMember AuthMember member, @CookieValue(REFRESH_COOKIE_KEY) String refreshToken){
        if(refreshToken == null){
            throw new CustomException(CommonErrorCode.UN_AUTHORIZATION);
        }
        TokenReissueResponse result = tokenReissueUseCase.reissue(
                new TokenReissueRequest(member.getId(), refreshToken));
        System.out.println(result +"||RESULT ===");
        return ResponseFactory.success("재발급 성공",new TokenReissueApiResponse(result.getAccessToken().getToken()));
    }

    private Cookie createRefreshCookie(String token){
        Cookie cookie = new Cookie(REFRESH_COOKIE_KEY, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        if(!isLocalEnvironment()){
            cookie.setSecure(true);
        }
        return cookie;
    }
    private boolean isLocalEnvironment(){
        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles){
            if(profile.equals("local")){
                return true;
            }
        }
        return false;
    }


}
