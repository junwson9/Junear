package hasix.junear.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById(Long id);
    Optional<Member> findByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider);
}
