package hasix.junear.billionaire.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "life_quotes")
public class LifeQuotes {

    @Id
    @Column(name = "life_quotes_id")
    private Long id;

    @Column(nullable = false)
    private String phrase;

    @Column(name = "billionaire_id")
    private Long billionaireId;
}
