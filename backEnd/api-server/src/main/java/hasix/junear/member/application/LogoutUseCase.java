package hasix.junear.member.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutUseCase {

    private final TokenService tokenService;
    
    public void logout(Long memberId){
        tokenService.deleteRefreshToken(memberId);
    }
}
