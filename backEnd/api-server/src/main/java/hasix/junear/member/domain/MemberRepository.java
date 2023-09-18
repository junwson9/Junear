package hasix.junear.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById(Long id);
    Optional<Member> findByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider);

    boolean exisitByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider);
    Member save(Member member);
}
