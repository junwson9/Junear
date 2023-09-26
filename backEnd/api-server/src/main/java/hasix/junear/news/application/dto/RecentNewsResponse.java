package hasix.junear.news.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RecentNewsResponse {

    private String industry;
    private String title;
    private String originUrl;
    private String imageUrl;
    private LocalDateTime times;
    private String media;

    public RecentNewsResponse(String industry, String title, String originUrl, String imageUrl, LocalDateTime times, String media) {
        this.industry = industry;
        this.title = title;
        this.originUrl = originUrl;
        this.imageUrl = imageUrl;
        this.times = times;
        this.media = media;
    }
}
