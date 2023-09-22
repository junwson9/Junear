package hasix.junear.billionaire.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Billionaire {

    @Id
    @Column(name = "billionaire_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;
}
