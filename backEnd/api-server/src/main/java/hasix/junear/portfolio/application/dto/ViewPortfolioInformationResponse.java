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
}
