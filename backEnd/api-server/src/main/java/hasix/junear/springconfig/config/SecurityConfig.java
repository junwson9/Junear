package hasix.junear.springconfig.config;


import hasix.junear.springconfig.security.CustomAuthenticationEntryPoint;
import hasix.junear.springconfig.security.JwtAuthenticationFilter;
import hasix.junear.springconfig.security.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final JwtExceptionFilter jwtExceptionFilter;

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
            .authenticationEntryPoint(customAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/member/info")
            .authenticated()
            .antMatchers("/")
            .permitAll();
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);
        return http.build();
    }

}
