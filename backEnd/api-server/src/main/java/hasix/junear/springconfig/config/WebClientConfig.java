package hasix.junear.springconfig.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient kakaoKAuthWebClient() {
        return WebClient.builder()
                        .baseUrl("https://kauth.kakao.com")
                        .build();
    }

    @Bean
    public WebClient kakaoKapiWebClient() {
        return WebClient.builder()
                        .baseUrl("https://kapi.kakao.com")
                        .defaultHeader("Content-type",
                                "application/x-www-form-urlencoded;charset=utf-8")
                        .build();
    }
}
