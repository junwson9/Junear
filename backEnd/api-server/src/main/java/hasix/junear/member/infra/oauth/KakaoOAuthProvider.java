package hasix.junear.member.infra.oauth;


import hasix.junear.member.infra.oauth.dto.KakaoOidcPublicKeyResponse;
import hasix.junear.member.infra.oidc.OidcPublicKey;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOAuthProvider {

    private final WebClient kakaoKAuthWebClient;

    public List<OidcPublicKey> getOidcPublicKeys(){
        log.info("[KAKAO-PUBLIC-KEY CALL]");
        String publicKeyUrl = "/.well-known/jwks.json";
        KakaoOidcPublicKeyResponse result = kakaoKAuthWebClient.get()
                                                             .uri(publicKeyUrl)
                                                             .retrieve()
                                                             .bodyToMono(
                                                                     KakaoOidcPublicKeyResponse.class)
                                                             .block();
        return result.getKeys();
    }

}
