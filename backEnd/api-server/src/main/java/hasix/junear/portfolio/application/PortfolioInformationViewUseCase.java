package hasix.junear.portfolio.application;

import hasix.junear.portfolio.application.dto.ViewPortfolioInformationResponse;
import hasix.junear.portfolio.application.dto.AssetsBundleApplication;
import hasix.junear.portfolio.domain.CorporationDetailsProvider;
import hasix.junear.portfolio.application.dto.MemberBundleApplication;
import hasix.junear.portfolio.domain.Portfolio;
import hasix.junear.portfolio.application.dto.PortfolioBundleApplication;
import hasix.junear.portfolio.domain.PortfolioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioInformationViewUseCase {

    private final PortfolioRepository portfolioRepository;
    private final CorporationDetailsProvider corporationDetailsProvider;

    public ViewPortfolioInformationResponse getPortfolio(Long memberId) {

        //포트폴리오 리스트 호출 by memberId
        List<Portfolio> portfolioList = portfolioRepository.findAllByMemberId(memberId);
        //기업 정보 리스트 호출 by 포폴 리스트
        //portfolioBundle
        //TODO Id 리스트를 통째로 보내고 반환값 리스트를 통쨰로 받는 SQL
        //TODO Resources를 받고, 해당 Resources를 세 개의 역할의 Resource로 분리하기 고민
        List<PortfolioBundleApplication> portfolioBundleApplicationList = portfolioList.stream()
                                                                                       .map(
                                                                                               portfolio -> PortfolioBundleApplication.from(
                                                                                                       corporationDetailsProvider.findViewCorporationDetailsResponseById(
                                                                                                               portfolio.getCorporationId()),
                                                                                                       portfolio)
                                                                                       )
                                                                                       .toList();

        //assetsBundle
        AssetsBundleApplication assetsBundleApplication = AssetsBundleApplication.from(
                portfolioBundleApplicationList);

        //memberBundle
        MemberBundleApplication memberBundleApplication = MemberBundleApplication.from(
                portfolioBundleApplicationList);

        return ViewPortfolioInformationResponse.from(assetsBundleApplication,
                portfolioBundleApplicationList,
                memberBundleApplication);
    }
}