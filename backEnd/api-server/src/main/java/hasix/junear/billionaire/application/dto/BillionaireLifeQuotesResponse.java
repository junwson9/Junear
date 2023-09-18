package hasix.junear.billionaire.application.dto;

import lombok.Builder;

public class BillionaireLifeQuotesResponse {
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
