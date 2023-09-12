package hasix.junear.springconfig.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // rest API , crsf 사용 X
        http.csrf()
            .disable();

        // 시큐리티 기본 login 방식 사용 X
        http.formLogin()
            .disable();

        // 세션 사용 X, 인증 실패시 entriyPoint로 이동된다.
        http.exceptionHandling()
            .authenticationEntryPoint(null)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors()
            .and()
            .authorizeRequests()
            .antMatchers("/")
            .permitAll();

        return http.build();
    }

}
