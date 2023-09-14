package hasix.junear.billionaire.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "life_quotes")
public class Phrase {

    @Id
    @Column(name = "life_quotes_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "billionaire_id")
    private Long billionaireId;
}
