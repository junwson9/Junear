package hasix.junear.member.application;

import hasix.junear.member.domain.OauthProvider;

public interface IdTokenValidator {
    OauthMemberInfo validateIdToken(String idToken, OauthProvider oauthProvider);
}
