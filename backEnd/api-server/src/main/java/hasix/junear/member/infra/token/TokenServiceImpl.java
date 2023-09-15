package hasix.junear.member.infra.token;

import hasix.junear.common.jwt.JwtProvider;
import hasix.junear.member.application.TokenService;
import hasix.junear.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtProvider jwtProvider;
    @Override
    public String createAccessToken(Member member) {
        return jwtProvider.createAccessToken(member.getId());
    }

    @Override
    public String createRefreshToken(Member member) {
        return jwtProvider.createRefreshToken();
    }

    @Override
    public void saveRefreshToken(String refreshToken, Long memberId) {
        log.info("Save refresh token memberId => " + memberId);
    }
}
