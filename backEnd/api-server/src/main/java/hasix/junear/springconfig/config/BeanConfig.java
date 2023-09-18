package hasix.junear.springconfig.config;


import hasix.junear.member.infra.oauth.KakaoOauthProperty;
import hasix.junear.member.infra.oidc.AbstractIdTokenValidator;
import hasix.junear.member.infra.oidc.KakaoIdTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

//    private final KakaoOauthProperty kakaoOauthProperty;
//    // 빈 이름 지정을 안하면 naming 규칙으로 생성됨 kakaoIdTokenValidator
//    @Bean()
//    public AbstractIdTokenValidator kakaoIdTokenValidator(){
//        return new KakaoIdTokenValidator(kakaoOauthProperty);
//    }
}
