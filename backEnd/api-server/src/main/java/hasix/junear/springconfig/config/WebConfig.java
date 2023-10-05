package hasix.junear.springconfig.config;


import hasix.junear.springconfig.config.auth.AuthenticatedMemberArgumentResolver;
import hasix.junear.springconfig.config.logging.LoggingFilter;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000","https://www.junear.cloud")
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }


    @Bean
    HandlerMethodArgumentResolver authenticatedMemberArgumentResolver(){
        return new AuthenticatedMemberArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticatedMemberArgumentResolver());
    }

    @Bean
    public FilterRegistrationBean commonLoggingFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new LoggingFilter());
        bean.setOrder(Integer.MIN_VALUE);
        return bean;
    }
}
