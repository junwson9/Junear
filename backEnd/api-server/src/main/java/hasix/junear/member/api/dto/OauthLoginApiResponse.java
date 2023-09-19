package hasix.junear.member.api.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@JsonNaming(SnakeCaseStrategy.class)
public class OauthLoginApiResponse {

    private String accessToken;
    public OauthLoginApiResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
