package hasix.junear.member.application.dto;


import hasix.junear.member.domain.Token;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenReissueRequest {

    private Long memberId;
    private Token refreshToken;

    public TokenReissueRequest(Long memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = Token.of(refreshToken);
    }
}
