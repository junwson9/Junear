package hasix.junear.portfolio.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EachAssetsApplication {

    private String corporationName;
    private Long corporationAsset;

    @Builder
    public EachAssetsApplication(String corporationName, Long corporationAsset) {
        this.corporationName = corporationName;
        this.corporationAsset = corporationAsset;
    }

    public static EachAssetsApplication from(String name, Long stockCount, Long stockClose) {
        return EachAssetsApplication.builder()
                                    .corporationName(name)
                                    .corporationAsset(stockCount * stockClose)
                                    .build();
    }
}
