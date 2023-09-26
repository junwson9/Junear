package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hasix.junear.portfolio.application.dto.ViewPortfolioInformationResponse;
import hasix.junear.portfolio.domain.AssetsBundle;
import hasix.junear.portfolio.domain.PortfolioBundle;
import hasix.junear.portfolio.domain.MemberBundle;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class PortFolioInformationApiResponse {

    private AssetsBundle assetsBundle;
    private List<PortfolioBundle> portfolioBundle;
    private MemberBundle memberBundle;

    @Builder
    public PortFolioInformationApiResponse(AssetsBundle assetsBundle,
            List<PortfolioBundle> portfolioBundle, MemberBundle memberBundle) {
        this.assetsBundle = assetsBundle;
        this.portfolioBundle = portfolioBundle;
        this.memberBundle = memberBundle;
    }

    public static PortFolioInformationApiResponse from(ViewPortfolioInformationResponse result) {

        return PortFolioInformationApiResponse.builder()
                                              .assetsBundle(result.getAssetsBundle())
                                              .portfolioBundle(result.getPortfolioBundle())
                                              .memberBundle(result.getMemberBundle())
                                              .build();
    }
}
