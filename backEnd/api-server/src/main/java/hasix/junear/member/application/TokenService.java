package hasix.junear.member.application;

import hasix.junear.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public interface TokenService {

    String createAccessToken(Member member);
    String createRefreshToken(Member member);

    void saveRefreshToken(String refreshToken, Long memberId);
}
