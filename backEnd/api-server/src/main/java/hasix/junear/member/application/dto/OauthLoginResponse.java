package hasix.junear.member.application.dto;


import hasix.junear.member.domain.Token;
import lombok.Getter;

@Getter
public class OauthLoginResponse {
    private final Token accessToken;
    private final Token refreshToken;

    public OauthLoginResponse(Token accessToken, Token refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
