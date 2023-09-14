package hasix.junear.member.infra.token;

import hasix.junear.member.application.TokenService;
import hasix.junear.member.domain.Member;
import org.springframework.stereotype.Component;


@Component
public class TokenServiceImpl implements TokenService {

    @Override
    public String createAccessToken(Member member) {
        return null;
    }

    @Override
    public String createRefreshToken(Member member) {
        return null;
    }

    @Override
    public void saveRefreshToken(String refreshToken, Long memberId) {

    }
}
