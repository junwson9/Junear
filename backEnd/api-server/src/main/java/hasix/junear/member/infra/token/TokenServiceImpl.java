package hasix.junear.member.infra.token;

import hasix.junear.member.infra.jwt.JwtProvider;
import hasix.junear.member.application.TokenService;
import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtProvider jwtProvider;
    @Override
    public Token createAccessToken(Member member) {
        String accessToken = jwtProvider.createAccessToken(member);
        return Token.of(accessToken);
    }

    @Override
    public Token createRefreshToken() {
        String refreshToken = jwtProvider.createRefreshToken();
        return Token.of(refreshToken);
    }

    @Override
    public void saveRefreshToken(Token refreshToken, Long memberId) {
        log.info("Save refresh token memberId => " + memberId);
    }
}
