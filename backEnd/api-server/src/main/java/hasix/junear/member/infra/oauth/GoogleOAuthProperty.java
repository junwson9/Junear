package hasix.junear.member.infra.oauth;

import hasix.junear.member.infra.oidc.OidcIdTokenCheckProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("oauth.google")
@Getter
@Setter
public class GoogleOAuthProperty {

    private String clientId;
    private String issuer;

    public OidcIdTokenCheckProperty toIdTokenProperty(){
        return new OidcIdTokenCheckProperty(issuer, clientId);
    }
}
