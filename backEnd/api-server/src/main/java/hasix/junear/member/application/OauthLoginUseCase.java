package hasix.junear.member.application;


import hasix.junear.member.application.dto.OauthLoginRequest;
import hasix.junear.member.application.dto.OauthLoginResponse;
import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.MemberRepository;
import hasix.junear.member.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OauthLoginUseCase {

    private final IdTokenValidator idTokenValidator;
    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    public OauthLoginResponse login(OauthLoginRequest oauthLoginRequest) {
        OauthMemberInfo oauthMemberInfo = idTokenValidator.validateIdToken(
                oauthLoginRequest.getIdToken(), oauthLoginRequest.getOauthProvider());

        Member oauthMember = memberRepository.findByOauthIdAndProvider(
                                                     oauthMemberInfo.getOauthId(),
                                                     oauthMemberInfo.getOauthProvider())
                                             .orElse(null);

        if (isRequireSignUp(oauthMember)) {
            oauthMember = memberRepository.save(oauthMemberInfo.toMember());
        }

        Token accessToken = tokenService.createAccessToken(oauthMember);
        Token refreshToken = tokenService.createRefreshToken();
        tokenService.saveRefreshToken(refreshToken, oauthMember.getId());
        return new OauthLoginResponse(accessToken, refreshToken);
    }

    private boolean isRequireSignUp(Member oauthMember) {
        return oauthMember == null;
    }
}
