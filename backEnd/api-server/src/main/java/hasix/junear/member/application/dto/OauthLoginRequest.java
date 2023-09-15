package hasix.junear.member.application.dto;

import hasix.junear.member.domain.OauthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OauthLoginRequest {

    private final String idToken;
    private final OauthProvider oauthProvider;


    @Builder
    public OauthLoginRequest(String idToken, OauthProvider oauthProvider) {
        this.idToken = idToken;
        this.oauthProvider = oauthProvider;
    }

}
