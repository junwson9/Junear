package hasix.junear.member.application;


import hasix.junear.common.exception.CustomException;
import hasix.junear.member.application.dto.TokenReissueRequest;
import hasix.junear.member.application.dto.TokenReissueResponse;
import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.MemberRepository;
import hasix.junear.member.domain.Token;
import hasix.junear.member.exception.AuthenticationErrorCode;
import hasix.junear.member.exception.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenReissueUseCase {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    public TokenReissueResponse reissue(TokenReissueRequest request){
        Member member = findMember(request.getMemberId());
        Token token = tokenService.getRefreshTokenFromMember(member)
                                  .orElseThrow(() -> new CustomException(
                                          AuthenticationErrorCode.REQUIRE_LOGIN));
        if(!isCorrespondToken(request.getRefreshToken(), token)){
            throw new CustomException(AuthenticationErrorCode.UN_AUTHORIZATION);
        }
        return new TokenReissueResponse(tokenService.createAccessToken(member));
    }

    private boolean isCorrespondToken(Token inputToken, Token savedToken){
        return inputToken.getToken().equals(savedToken.getToken());
    }

    private Member findMember(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()->new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));
    }

}
