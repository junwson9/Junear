package hasix.junear.member.application;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import hasix.junear.member.application.dto.SampleRequest;
import hasix.junear.member.application.dto.SampleResponse;
import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.MemberRepository;
import hasix.junear.member.domain.OauthId;
import hasix.junear.member.domain.OauthProvider;
import hasix.junear.member.domain.ROLE;
import hasix.junear.member.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


@ExtendWith(MockitoExtension.class)
class SampleUseCaseTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    SampleUseCase sampleUseCase;


    private OauthId oauthId = new OauthId("123");
    private OauthProvider requestProvider = OauthProvider.KAKAO;
    SampleRequest request = new SampleRequest(oauthId, requestProvider);

    @DisplayName("Sample 유저를 요청하면 Response 을 반환한다.")
    @Test
    void sample_return_response() {
        //given
        Member member = mockMember();
        given(memberRepository.findByOauthIdAndProvider(oauthId, requestProvider)).willReturn(member);

        //when
        SampleResponse result = sampleUseCase.getSample(request);

        //then
        assertThat(result.getMemberId()).isEqualTo(member.getId());
        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @DisplayName("없는 유저를 요청하면 예외를 반환한다.")
    @Test
    void nonExistMemberReturnException() {
        //given
        given(memberRepository.findByOauthIdAndProvider(oauthId, requestProvider)).willReturn(null);
        //when //then

        assertThatThrownBy(()-> {
            sampleUseCase.getSample(request);
        }).isInstanceOf(MemberException.class);
    }


    private Member mockMember() {
        Member member = Member.builder()
                            .name("name")
                            .oauthId(oauthId)
                            .profileImage("..")
                            .oauthProvider(requestProvider)
                            .role(ROLE.USER)
                            .build();
        // auto_increment인 id 값을 모킹해준다.
        ReflectionTestUtils.setField(member,"id",1L);
        return member;
    }
}