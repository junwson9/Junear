package hasix.junear.portfolio.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
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
