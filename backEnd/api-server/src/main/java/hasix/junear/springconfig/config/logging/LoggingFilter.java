package hasix.junear.springconfig.config.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        ReusableRequestWrapper requestWrapper = new ReusableRequestWrapper(request);
        AccessLogDto logging = logging(requestWrapper);
        log.info(objectMapper.writeValueAsString(logging));
        filterChain.doFilter(requestWrapper, response);
    }

    private AccessLogDto logging(ReusableRequestWrapper request) throws IOException {
        return AccessLogDto.builder()
                           .requestMethod(request.getMethod())
                           .requestUrl(request.getRequestURI())
                           .requestIp(RequestLogUtil.getIp(request))
                           .requestBody(RequestLogUtil.getBody(request))
                           .build();
    }
}

