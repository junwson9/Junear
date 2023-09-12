package hasix.junear.member.domain;


import hasix.junear.common.model.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_oauth_member",
                columnNames = {"oauth_provider","oauth_id"}
        )
    }
)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "profile_image")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "oauth_provider")
    private OauthProvider oauthProvider;

    @Embedded
    private OauthId oauthId = new OauthId();

    @Column(nullable = false)
    private ROLE role;

    @Builder
    public Member(String name, String profileImage, OauthProvider oauthProvider, OauthId oauthId,
            ROLE role) {
        this.name = name;
        this.profileImage = profileImage;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.role = role;
    }
}
