package hasix.junear.member.infra.mysql;

import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.MemberRepository;
import hasix.junear.member.domain.OauthId;
import hasix.junear.member.domain.OauthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;
    @Override
    public Member findById(Long id) {
        return jpaMemberRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Member findByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider) {
        return jpaMemberRepository.findByOauthIdAndOauthProvider(oAuthId ,oAuthProvider)
                .orElse(null);
    }
}
