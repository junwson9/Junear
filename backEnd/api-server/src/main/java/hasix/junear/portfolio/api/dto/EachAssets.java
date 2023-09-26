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

    public static EachAssets from(String name, Long stockCount, Long stockClose) {
        return EachAssets.builder()
                         .corporationName(name)
                         .corporationAsset(stockCount * stockClose)
                         .build();
    }
}
