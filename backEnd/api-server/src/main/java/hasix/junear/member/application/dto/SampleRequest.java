package hasix.junear.member.application.dto;


import hasix.junear.member.domain.OauthId;
import hasix.junear.member.domain.OauthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SampleRequest {

    private final OauthId oauthId;
    private final OauthProvider oauthProvider;


    @Builder
    public SampleRequest(OauthId oauthId, OauthProvider oauthProvider) {
        this.oauthId = oauthId;
        this.oauthProvider = oauthProvider;
    }
}
