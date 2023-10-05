package hasix.junear.springconfig.config.logging;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccessLogDto {

    private String method;

    private String url;

    private String ip;

    private String body;

    private String origin;

    @Builder

    public AccessLogDto(String method, String url, String ip, String body, String origin) {
        this.method = method;
        this.url = url;
        this.ip = ip;
        this.body = body;
        this.origin = origin;
    }
}

