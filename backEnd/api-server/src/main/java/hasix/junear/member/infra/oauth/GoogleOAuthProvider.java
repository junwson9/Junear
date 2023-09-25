package hasix.junear.member.infra.oauth;


import hasix.junear.member.infra.oauth.dto.GoogleOpenSearchDocsResponse;
import hasix.junear.member.infra.oauth.dto.OidcPublicKeyResponse;
import hasix.junear.member.infra.oidc.OidcPublicKey;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class GoogleOAuthProvider {

    private final WebClient googleAccountWebClient;
    private final WebClient defaultWebClient;


    public GoogleOpenSearchDocsResponse getGoogleOpenSearchDocs(){
        String uri = "/.well-known/openid-configuration";
        return googleAccountWebClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GoogleOpenSearchDocsResponse.class)
                .block();

    }

    public List<OidcPublicKey> getPublcKeys(String uri){
        OidcPublicKeyResponse result = defaultWebClient.get()
                                                      .uri(uri)
                                                      .retrieve()
                                                      .bodyToMono(OidcPublicKeyResponse.class)
                                                      .block();
        return result.getKeys();
    }

}
