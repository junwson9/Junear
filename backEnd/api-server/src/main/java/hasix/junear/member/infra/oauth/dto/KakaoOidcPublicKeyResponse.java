package hasix.junear.member.infra.oauth.dto;

import hasix.junear.member.infra.oidc.OidcPublicKey;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class KakaoOidcPublicKeyResponse {

    private List<OidcPublicKey> keys;

}
