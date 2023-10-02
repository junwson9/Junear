package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class EachAssets {

    private String corporationName;
    private Long corporationAsset;

    @Builder
    public EachAssets(String corporationName, Long corporationAsset) {
        this.corporationName = corporationName;
        this.corporationAsset = corporationAsset;
    }

}
