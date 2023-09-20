package hasix.junear.member.infra.repository;

import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.MemberRepository;
import hasix.junear.member.domain.OauthId;
import hasix.junear.member.domain.OauthProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public Optional<Member> findById(Long id) {
        return jpaMemberRepository.findById(id);
    }

    @Override
    public Optional<Member> findByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider) {
        return jpaMemberRepository.findByOauthIdAndOauthProvider(oAuthId, oAuthProvider);
    }

    @Override
    public boolean exisitByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider) {
        return jpaMemberRepository.existsByOauthIdAndOauthProvider(oAuthId, oAuthProvider);
    }

    @Override
    public Member save(Member member) {
        return jpaMemberRepository.save(member);
    }
}
