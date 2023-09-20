package hasix.junear.member.application;

import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.Token;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface TokenService {

    Token createAccessToken(Member member);
    Token createRefreshToken();
    void saveRefreshToken(Token refreshToken, Long memberId);

    void deleteRefreshToken(Long memberId);

    Optional<Token> getRefreshTokenFromMember(Member member);
}
