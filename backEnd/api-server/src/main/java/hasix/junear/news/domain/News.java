package hasix.junear.news.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class News {

    @Id
    private Long id;

    @Column(name = "industry_id")
    private Long industryId;

    private String title;

    @Column(name = "origin_url")
    private String originUrl;

    @Column(name = "image_url")
    private String imageUrl;

    private String times;

    private String media;

}
