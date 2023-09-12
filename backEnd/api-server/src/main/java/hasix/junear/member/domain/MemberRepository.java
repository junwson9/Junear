package hasix.junear.member.domain;

public interface MemberRepository {

        Member findById(Long id);
        Member findByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider);
}
