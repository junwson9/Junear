package hasix.junear.member.api;


import hasix.junear.common.response.ResponseFactory;
import hasix.junear.member.application.LogoutUseCase;
import hasix.junear.springconfig.config.auth.AuthMember;
import hasix.junear.springconfig.config.auth.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApi {

    private final LogoutUseCase logoutUseCase;

    @GetMapping("/api/member/logout")
    public ResponseEntity<?> logout(@AuthenticatedMember AuthMember member){
        logoutUseCase.logout(member.getId());
        return ResponseFactory.success("로그아웃 성공");
    }
}
