package hasix.junear.member.infra.mysql;

import hasix.junear.member.domain.Member;
import hasix.junear.member.domain.OauthId;
import hasix.junear.member.domain.OauthProvider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauthIdAndOauthProvider(OauthId oAuthId, OauthProvider oAuthProvider);
}
