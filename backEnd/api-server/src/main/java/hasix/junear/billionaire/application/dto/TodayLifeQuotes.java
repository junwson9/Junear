package hasix.junear.billionaire.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class TodayLifeQuotes {
    private String name;
    private String imageUrl;
    private String phrase;

    @Builder
    public TodayLifeQuotes(String name, String imageUrl, String phrase) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.phrase = phrase;
    }

//    public static TodayLifeQuotes from(LifeQuotesResponse response) {
//        return TodayLifeQuotes.builder()
//                .name(response.getName())
//                .phrase(response.getPhrase())
//                .imageUrl(response.getImageUrl())
//                .build();
//    }
}
