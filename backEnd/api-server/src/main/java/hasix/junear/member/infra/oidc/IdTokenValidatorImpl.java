package hasix.junear.member.infra.oidc;

import hasix.junear.member.application.IdTokenValidator;
import hasix.junear.member.application.OauthMemberInfo;
import hasix.junear.member.domain.OauthProvider;
import hasix.junear.member.exception.MemberErrorCode;
import hasix.junear.member.exception.MemberException;
import org.springframework.stereotype.Component;

@Component
public class IdTokenValidatorImpl implements IdTokenValidator {


    @Override
    public OauthMemberInfo validateIdToken(String idToken, OauthProvider oauthProvider) {
        if(oauthProvider.equals(OauthProvider.KAKAO)){

        }else if(oauthProvider.equals(OauthProvider.GOOGLE)){

        }else {
            throw new MemberException(MemberErrorCode.NOT_SUPPORT_OAUTH_PROVIDER);
        }
        return null;
    }
}
