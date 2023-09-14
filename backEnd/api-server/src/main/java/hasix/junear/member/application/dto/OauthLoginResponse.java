package hasix.junear.member.application.dto;


import lombok.Getter;

@Getter
public class OauthLoginResponse {
    private final String accessToken;
    private final String refreshToken;

    public OauthLoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
