package hasix.junear.member.application.dto;


import hasix.junear.member.domain.Token;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenReissueResponse {

    private Token accessToken;

    public TokenReissueResponse(Token accessToken) {
        this.accessToken = accessToken;
    }
}
