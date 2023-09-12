package hasix.junear.member.application;


import hasix.junear.member.application.dto.SampleRequest;
import hasix.junear.member.application.dto.SampleResponse;
import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.MemberRepository;
import hasix.junear.member.domain.OauthId;
import hasix.junear.member.domain.OauthProvider;
import hasix.junear.member.exception.MemberErrorCode;
import hasix.junear.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SampleUseCase {

    private final MemberRepository memberRepository;

    public SampleResponse getSample(SampleRequest sampleRequest) {
        Member member = findMember(sampleRequest.getOauthProvider(), sampleRequest.getOauthId());
        return SampleResponse.from(member);
    }

    private Member findMember(OauthProvider oAuthProvider, OauthId oAuthId) {
        return memberRepository.findByOauthIdAndProvider(oAuthId, oAuthProvider)
                               .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));
    }
}
