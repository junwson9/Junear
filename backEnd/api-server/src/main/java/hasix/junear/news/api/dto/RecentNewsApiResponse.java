package hasix.junear.news.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hasix.junear.news.application.dto.RecentNewsResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class RecentNewsApiResponse {

    private String industry;
    private String title;
    private String originUrl;
    private String imageUrl;
    private String times;
    private String media;

    @Builder
    public RecentNewsApiResponse(String industry, String title, String originUrl, String imageUrl, String times, String media) {
        this.industry = industry;
        this.title = title;
        this.originUrl = originUrl;
        this.imageUrl = imageUrl;
        this.times = times;
        this.media = media;
    }

    public static RecentNewsApiResponse from(RecentNewsResponse response) {
        return RecentNewsApiResponse.builder()
                .industry(response.getIndustry())
                .title(response.getTitle())
                .originUrl(response.getOriginUrl())
                .imageUrl(response.getImageUrl())
                .times(response.getTimes())
                .media(response.getMedia())
                .build();
    }
}
