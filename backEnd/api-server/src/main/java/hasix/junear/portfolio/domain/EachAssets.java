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
}
