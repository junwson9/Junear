package hasix.junear.member.application;


import hasix.junear.common.exception.CustomException;
import hasix.junear.member.application.dto.MemberResponse;
import hasix.junear.member.domain.MemberRepository;
import hasix.junear.member.exception.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberSearchUseCase {

    private final MemberRepository memberRepository;

    public MemberResponse searchDetail(Long memberId){
        return memberRepository.findById(memberId)
                .map(MemberResponse::from)
                .orElseThrow(()-> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));
    }
}
