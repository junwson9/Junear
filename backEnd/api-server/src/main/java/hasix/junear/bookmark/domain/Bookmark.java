package hasix.junear.bookmark.domain;

import lombok.Builder;

import javax.persistence.*;

@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    Long id;

    @Column(name = "member_id")
    Long memberId;

    @Column(name = "corporation_id")
    Long corporationId;

    @Builder
    public Bookmark(Long memberId, Long corporationId) {
        this.memberId = memberId;
        this.corporationId = corporationId;
    }
}
