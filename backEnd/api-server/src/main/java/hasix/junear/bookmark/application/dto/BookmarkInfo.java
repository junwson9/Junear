package hasix.junear.bookmark.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkInfo {
    private Long corporationId;
    private String corporationName;
    private String industry;

    public BookmarkInfo(Long corporationId, String corporationName, String industry) {
        this.corporationId = corporationId;
        this.corporationName = corporationName;
        this.industry = industry;
    }
}
