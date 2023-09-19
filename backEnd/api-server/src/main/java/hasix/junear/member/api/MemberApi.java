package hasix.junear.member.api;


import hasix.junear.common.response.ResponseFactory;
import hasix.junear.member.api.dto.MemberInfoApiResponse;
import hasix.junear.member.application.MemberSearchUseCase;
import hasix.junear.member.application.dto.MemberResponse;
import hasix.junear.springconfig.config.auth.AuthMember;
import hasix.junear.springconfig.config.auth.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {


    private final MemberSearchUseCase memberSearchUseCase;


    @GetMapping("/api/member/info")
    public ResponseEntity<?> getMemberInfo(@AuthenticatedMember AuthMember member){
        MemberResponse memberResponse = memberSearchUseCase.searchDetail(member.getId());
        return ResponseFactory.success("회원 조회 성공", new MemberInfoApiResponse(memberResponse));
    }
}
