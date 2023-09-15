package hasix.junear.corporation.api.dto;

import hasix.junear.member.application.dto.SampleRequest;
import hasix.junear.member.domain.OauthId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CorporationApiRequest {

    public SampleRequest to() {
        return SampleRequest.builder()
                            .build();
    }

}
