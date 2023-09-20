package hasix.junear.corporation.api.dto;

import hasix.junear.member.application.dto.SampleRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CorporationDetailsApiRequest {

    public SampleRequest to() {
        return SampleRequest.builder()
                            .build();
    }

}
