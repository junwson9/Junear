package hasix.junear.member.application;

import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.OauthId;
import hasix.junear.member.domain.OauthProvider;
import hasix.junear.member.domain.Role;
import lombok.Builder;
import lombok.Getter;


@Getter
public class OauthMemberInfo {

    private OauthId oauthId;
    private OauthProvider oauthProvider;
    private String name;
    private String profileImageUrl;

    @Builder
    public OauthMemberInfo(String oauthId, OauthProvider oauthProvider, String name,
            String profileImageUrl) {
        this.oauthId = OauthId.of(oauthId);
        this.oauthProvider = oauthProvider;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public Member toMember() {
        return Member.builder()
                     .oauthProvider(oauthProvider)
                     .name(name)
                     .oauthId(oauthId)
                     .profileImage(profileImageUrl)
                     .role(Role.MEMBER)
                     .build();
    }
}
