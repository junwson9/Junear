package hasix.junear.member.application.dto;

import hasix.junear.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long memberId;
    private String name;
    private String profileImage;

    @Builder
    public MemberResponse(Long memberId, String name, String profileImage) {
        this.memberId = memberId;
        this.name = name;
        this.profileImage = profileImage;
    }

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                             .memberId(member.getId())
                             .name(member.getName())
                             .profileImage(member.getProfileImage())
                             .build();
    }
}
