package hasix.junear.member.application.dto;

import hasix.junear.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SampleResponse {

    private Long memberId;
    private String name;

    @Builder
    private SampleResponse(Long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public static SampleResponse from(Member member) {
        return SampleResponse.builder()
                             .memberId(member.getId())
                             .name(member.getName())
                             .build();
    }
}
