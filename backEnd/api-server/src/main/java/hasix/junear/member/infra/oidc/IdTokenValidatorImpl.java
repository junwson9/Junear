package hasix.junear.member.infra.oidc;

import hasix.junear.common.exception.CustomException;
import hasix.junear.member.application.IdTokenValidator;
import hasix.junear.member.application.OauthMemberInfo;
import hasix.junear.member.domain.OauthProvider;
import hasix.junear.member.exception.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdTokenValidatorImpl implements IdTokenValidator {

    private final KakaoIdTokenValidator kakaoIdTokenValidator;

    @Override
    public OauthMemberInfo validateIdToken(String idToken, OauthProvider oauthProvider) {
        if (oauthProvider.equals(OauthProvider.KAKAO)) {
            return kakaoIdTokenValidator.validateIdToken(idToken);
        } else if (oauthProvider.equals(OauthProvider.GOOGLE)) {
            return null;
        } else {
            throw new CustomException(MemberErrorCode.NOT_SUPPORT_OAUTH_PROVIDER);
        }
    }
}
