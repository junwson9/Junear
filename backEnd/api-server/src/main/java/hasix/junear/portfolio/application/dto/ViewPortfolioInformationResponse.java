package hasix.junear.portfolio.application.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ViewPortfolioInformationResponse {

    private AssetsBundleApplication assetsBundleApplication;
    private List<PortfolioBundleApplication> portfolioBundleApplication;
    private MemberBundleApplication memberBundleApplication;

    @Builder
    public ViewPortfolioInformationResponse(AssetsBundleApplication assetsBundleApplication,
            List<PortfolioBundleApplication> portfolioBundleApplication, MemberBundleApplication memberBundleApplication) {
        this.assetsBundleApplication = assetsBundleApplication;
        this.portfolioBundleApplication = portfolioBundleApplication;
        this.memberBundleApplication = memberBundleApplication;
    }

    public static ViewPortfolioInformationResponse from(AssetsBundleApplication assetsBundleApplication,
            List<PortfolioBundleApplication> portfolioBundleApplicationList, MemberBundleApplication memberBundleApplication) {
        return ViewPortfolioInformationResponse.builder()
                                               .assetsBundleApplication(assetsBundleApplication)
                                               .portfolioBundleApplication(portfolioBundleApplicationList)
                                               .memberBundleApplication(memberBundleApplication)
                                               .build();
    }
}
