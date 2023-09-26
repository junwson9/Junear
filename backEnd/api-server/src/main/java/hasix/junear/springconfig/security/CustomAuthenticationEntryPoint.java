package hasix.junear.springconfig.security;

import hasix.junear.common.exception.CommonErrorCode;
import hasix.junear.common.exception.ErrorCode;
import hasix.junear.common.response.ResponseFactory;
import hasix.junear.member.exception.AuthenticationErrorCode;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        ErrorCode errorCode = AuthenticationErrorCode.REQUIRE_LOGIN;
        ResponseFactory.fail(response, authException.getMessage(), errorCode);
    }
}
