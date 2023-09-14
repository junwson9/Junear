package hasix.junear.billionaire.api.dto;

import lombok.Builder;

public class BillionaireApiResponse {

    private String name;
    private String imageUrl;
    private String phrase;

    @Builder
    public BillionaireApiResponse(String name, String imageUrl, String phrase) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.phrase = phrase;
    }


}
