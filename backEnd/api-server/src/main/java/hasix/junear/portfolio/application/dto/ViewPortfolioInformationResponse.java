package hasix.junear.portfolio.application.dto;

import hasix.junear.portfolio.domain.AssetsBundle;
import hasix.junear.portfolio.domain.PortfolioBundle;
import hasix.junear.portfolio.domain.MemberBundle;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewPortfolioInformationResponse {

    private AssetsBundle assetsBundle;
    private List<PortfolioBundle> portfolioBundle;
    private MemberBundle memberBundle;

    @Builder
    public ViewPortfolioInformationResponse(AssetsBundle assetsBundle,
            List<PortfolioBundle> portfolioBundle, MemberBundle memberBundle) {
        this.assetsBundle = assetsBundle;
        this.portfolioBundle = portfolioBundle;
        this.memberBundle = memberBundle;
    }

    public static ViewPortfolioInformationResponse from(AssetsBundle assetsBundle,
            List<PortfolioBundle> portfolioBundleList, MemberBundle memberBundle) {
        return ViewPortfolioInformationResponse.builder()
                                               .assetsBundle(assetsBundle)
                                               .portfolioBundle(portfolioBundleList)
                                               .memberBundle(memberBundle)
                                               .build();
    }
}
