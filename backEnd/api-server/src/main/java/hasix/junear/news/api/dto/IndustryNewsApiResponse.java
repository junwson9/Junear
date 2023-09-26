package hasix.junear.news.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hasix.junear.news.application.dto.IndustryNewsResponse;
import hasix.junear.news.application.dto.RecentNewsResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class IndustryNewsApiResponse {

    private String industry;
    private String title;
    private String originUrl;
    private String imageUrl;
    private String times;
    private String media;

    @Builder
    public IndustryNewsApiResponse(String industry, String title, String originUrl, String imageUrl, String times, String media) {
        this.industry = industry;
        this.title = title;
        this.originUrl = originUrl;
        this.imageUrl = imageUrl;
        this.times = times;
        this.media = media;
    }

    public static IndustryNewsApiResponse from(IndustryNewsResponse response, String times) {
        return IndustryNewsApiResponse.builder()
                .industry(response.getIndustry())
                .title(response.getTitle())
                .originUrl(response.getOriginUrl())
                .imageUrl(response.getImageUrl())
                .times(times)
                .media(response.getMedia())
                .build();
    }
}
