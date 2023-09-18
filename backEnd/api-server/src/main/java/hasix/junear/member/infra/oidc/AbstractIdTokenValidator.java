package hasix.junear.member.infra.oidc;

import hasix.junear.common.exception.CustomException;
import hasix.junear.member.infra.jwt.IdTokenResolver;
import hasix.junear.member.application.OauthMemberInfo;
import hasix.junear.member.exception.MemberErrorCode;
import java.util.List;
import java.util.Map;

public abstract class AbstractIdTokenValidator {

    private final OidcIdTokenCheckProperty oidcIdTokenCheckProperty;
    private final IdTokenResolver idTokenResolver;

    public AbstractIdTokenValidator(OidcIdTokenCheckProperty oidcIdTokenCheckProperty,
            IdTokenResolver idTokenResolver) {
        this.oidcIdTokenCheckProperty = oidcIdTokenCheckProperty;
        this.idTokenResolver = idTokenResolver;
    }

    public OauthMemberInfo validateIdToken(String idToken) {
        String kid = idTokenResolver.getKidFromHeader(idToken);
        OidcPublicKey publicKey = getPublicKey(kid);

        String issuer = oidcIdTokenCheckProperty.getIssuer();
        String audience = oidcIdTokenCheckProperty.getAudience();

        Map<String, Object> payload = idTokenResolver.validateIdToken(idToken, issuer, audience,
                publicKey.getN(), publicKey.getE());

        return extractMemberInfoFromPayload(payload);
    }

    private OidcPublicKey getPublicKey(String kid) {
        return getOIDCPublicKeys()
                .stream()
                .filter((oidcPublicKey -> oidcPublicKey.getKid()
                                                       .equals(kid)))
                .findAny()
                .orElseThrow(() -> new CustomException(MemberErrorCode.INVALID_ID_TOKEN));
    }

    abstract List<OidcPublicKey> getOIDCPublicKeys();

    abstract OauthMemberInfo extractMemberInfoFromPayload(Map<String, Object> payload);
}
