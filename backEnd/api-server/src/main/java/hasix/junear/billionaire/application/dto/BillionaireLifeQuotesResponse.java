package hasix.junear.billionaire.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BillionaireLifeQuotesResponse implements Serializable {
    private String name;
    private String imageUrl;
    private String phrase;

    @Builder
    public BillionaireLifeQuotesResponse(String name, String imageUrl, String phrase) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.phrase = phrase;
    }
}
