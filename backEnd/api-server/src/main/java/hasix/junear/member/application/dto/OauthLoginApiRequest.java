package hasix.junear.member.application.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hasix.junear.member.domain.OauthProvider;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming (SnakeCaseStrategy.class)
public class OauthLoginApiRequest {

    @NotBlank
    private String idToken;

    @NotNull
    private OauthProvider oauthProvider;
}
