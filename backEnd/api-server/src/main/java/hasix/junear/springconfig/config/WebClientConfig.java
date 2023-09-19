package hasix.junear.springconfig.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final WebClientLoggingFilter webClientLoggingFilter;

    @Bean
    public WebClient kakaoKAuthWebClient() {
        return WebClient.builder()
                        .baseUrl("https://kauth.kakao.com")
                        .filter(webClientLoggingFilter)
                        .build();
    }

    @Bean
    public WebClient kakaoKapiWebClient() {
        return WebClient.builder()
                        .baseUrl("https://kapi.kakao.com")
                        .filter(webClientLoggingFilter)
                        .defaultHeader("Content-type",
                                "application/x-www-form-urlencoded;charset=utf-8")
                        .build();
    }

    @Bean
    public WebClient googleAccountWebClient() {
        return WebClient.builder()
                        .baseUrl("https://accounts.google.com")
                        .filter(webClientLoggingFilter)
                        .build();
    }

    @Bean
    public WebClient defaultWebClient(){
        return WebClient.builder()
                .filter(webClientLoggingFilter)
                .build();
    }
}
