package hasix.junear.member.api.dto;

import hasix.junear.member.application.dto.SampleResponse;
import lombok.Builder;

public class SampleApiResponse {

    private Long memberId;
    private String name;
        @Builder
    public SampleApiResponse(Long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public static SampleApiResponse from(SampleResponse result){
        return SampleApiResponse.builder()
                .memberId(result.getMemberId())
                .name(result.getName())
                .build();
    }
}
