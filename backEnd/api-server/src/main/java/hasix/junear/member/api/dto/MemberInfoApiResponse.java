package hasix.junear.member.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hasix.junear.member.application.dto.MemberResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class MemberInfoApiResponse {

    private Long memberId;
    private String name;
    private String profileImage;


    public MemberInfoApiResponse(Long memberId, String name, String profileImage) {
        this.memberId = memberId;
        this.name = name;
        this.profileImage = profileImage;
    }

    public MemberInfoApiResponse(MemberResponse response) {
        this.memberId = response.getMemberId();
        this.name = response.getName();
        this.profileImage = response.getProfileImage();
    }
}
