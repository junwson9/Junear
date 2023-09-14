package hasix.junear.member.infra.oidc;

import java.util.List;
import java.util.Map;

public abstract class AbstractIdTokenValidator {

    public void validateIdToken(String idToken) {

    }

    private OIDCPublicKey getJwkKeyFromPublickeys(String kid) {
        List<OIDCPublicKey> oidcPublicKeys = getOIDCPublicKeys();
        return oidcPublicKeys.stream()
                             .filter((key) -> key.getKid()
                                                 .equals(kid))
                             .findAny()
                             .orElseThrow(() -> new RuntimeException(""));
    }

    abstract List<OIDCPublicKey> getOIDCPublicKeys();

    abstract void extractMemberInfoFromPayload(Map<String, Object> payload);
}
