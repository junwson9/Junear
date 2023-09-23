package hasix.junear.springconfig.security;

import hasix.junear.common.exception.CommonErrorCode;
import hasix.junear.common.exception.CustomException;
import hasix.junear.common.response.ResponseFactory;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        }catch (CustomException e){
            ResponseFactory.fail(response, e.getMessage(), e.getErrorCode());
        }catch (Exception e){
            ResponseFactory.fail(response, e.getMessage(), CommonErrorCode.SERVER_ERROR);
        }
    }

}
