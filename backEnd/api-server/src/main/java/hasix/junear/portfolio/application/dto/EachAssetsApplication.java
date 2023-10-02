package hasix.junear.portfolio.application.dto;

import hasix.junear.portfolio.api.dto.EachAssets;
import java.util.List;
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

    public static List<EachAssets> toEachAssetsList(List<EachAssetsApplication> eachAssetApplicationList) {
        return eachAssetApplicationList.stream()
                .map(EachAssetsApplication::toEachAssets)
                .toList();
    }

    private static EachAssets toEachAssets(EachAssetsApplication eachAssetApplication) {
        return EachAssets.builder()
                .corporationName(eachAssetApplication.getCorporationName())
                .corporationAsset(eachAssetApplication.getCorporationAsset())
                .build();
    }
}
