package hasix.junear.member.api.dto;

import hasix.junear.member.application.dto.SampleRequest;
import hasix.junear.member.domain.OauthId;
import hasix.junear.member.domain.OauthProvider;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SampleApiRequest {

    @NotBlank
    private String oauthId;

    @NotNull
    private OauthProvider oauthProvider;

    public SampleRequest to() {
        return SampleRequest.builder()
                            .oauthId(new OauthId(oauthId))
                            .oauthProvider(oauthProvider)
                            .build();
    }
}
