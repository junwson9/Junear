package hasix.junear.portfolio.application;

import hasix.junear.portfolio.application.dto.ViewPortfolioInformationResponse;
import hasix.junear.portfolio.domain.AssetsBundle;
import hasix.junear.portfolio.domain.CorporationDetailsProvider;
import hasix.junear.portfolio.domain.MemberBundle;
import hasix.junear.portfolio.domain.Portfolio;
import hasix.junear.portfolio.domain.PortfolioBundle;
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
        List<PortfolioBundle> portfolioBundleList = portfolioList.stream()
                                                                 .map(
                                                                         portfolio -> PortfolioBundle.from(
                                                                                 corporationDetailsProvider.findViewCorporationDetailsResponseById(
                                                                                         portfolio.getCorporationId()),
                                                                                 portfolio)
                                                                 )
                                                                 .toList();
        //assetsBundle
        AssetsBundle assetsBundle = AssetsBundle.from(portfolioBundleList);

        //memberBundle
        MemberBundle memberBundle = MemberBundle.from(portfolioBundleList);

        return ViewPortfolioInformationResponse.from(assetsBundle, portfolioBundleList, memberBundle);
    }
}