package hasix.junear.portfolio.api;

import hasix.junear.common.response.ResponseFactory;
import hasix.junear.portfolio.api.dto.PortFolioAddApiRequest;
import hasix.junear.portfolio.api.dto.PortFolioCreateApiRequest;
import hasix.junear.portfolio.api.dto.PortFolioInformationApiResponse;
import hasix.junear.portfolio.api.dto.PortFolioModifyApiRequest;
import hasix.junear.portfolio.application.EachPortfolioAddUseCase;
import hasix.junear.portfolio.application.EachPortfolioModifyUseCase;
import hasix.junear.portfolio.application.EachPortfolioRemoveUseCase;
import hasix.junear.portfolio.application.PortfolioCreateUseCase;
import hasix.junear.portfolio.application.PortfolioInformationViewUseCase;
import hasix.junear.portfolio.application.dto.AddEachPortfolioRequest;
import hasix.junear.portfolio.application.dto.CreatePortfolioRequest;
import hasix.junear.portfolio.application.dto.ModifyEachPortfolioRequest;
import hasix.junear.portfolio.application.dto.RemoveEachPortfolioRequest;
import hasix.junear.portfolio.application.dto.ViewPortfolioInformationResponse;
import hasix.junear.springconfig.config.portfolio.NotEmptyList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio" )
@RequiredArgsConstructor
public class PortfolioApi {

    private final EachPortfolioAddUseCase eachPortfolioAddUseCase;
    private final PortfolioCreateUseCase portfolioCreateUseCase;
    private final EachPortfolioModifyUseCase eachPortfolioModifyUseCase;
    private final EachPortfolioRemoveUseCase eachPortfolioRemoveUseCase;
    private final PortfolioInformationViewUseCase portfolioInformationViewUseCase;

    //포트폴리오 초기 생성; portfolioCreate
    @PostMapping("/init")
    public ResponseEntity<?> portfolioCreate(@RequestParam Long memberId,
           @Valid @RequestBody PortFolioCreateApiRequest request) {

        portfolioCreateUseCase.createPortfolio(CreatePortfolioRequest.from(memberId, request));

        return ResponseFactory.success("포트폴리오 초기 생성 성공", request);
    }

    //포트폴리오 조회; portfolioInformation
    @GetMapping
    public ResponseEntity<?> portfolioInformation(@RequestParam Long memberId){

        ViewPortfolioInformationResponse result = portfolioInformationViewUseCase.getPortfolio(memberId);

        return ResponseFactory.success("포트폴리오 조회 성공", PortFolioInformationApiResponse.from(result));
    }

    //포트폴리오 기업 추가; portfolioAdd
    @PostMapping
    public ResponseEntity<?> portfolioAdd(@RequestParam Long memberId,
            @Valid @RequestBody PortFolioAddApiRequest request) {

        eachPortfolioAddUseCase.addPortfolio(AddEachPortfolioRequest.from(memberId, request));

        return ResponseFactory.success("포트폴리오 추가 성공");
    }

    //포트폴리오 기업 삭제; portfolioRemove
    @DeleteMapping("/{corporation_id}")
    public ResponseEntity<?> portfolioRemove(@RequestParam Long memberId,
            @PathVariable("corporation_id") Long corporationId) {

        eachPortfolioRemoveUseCase.deletePortfolio(RemoveEachPortfolioRequest.from(memberId, corporationId));

        return ResponseFactory.success("포트폴리오 삭제 성공");
    }

    //포트폴리어 기업 수정(수량 및 평단가); portfolioModify
    @PatchMapping
    public ResponseEntity<?> portfolioModify(@RequestParam Long memberId,
            @Valid @RequestBody PortFolioModifyApiRequest portFolioModifyApiRequest) {

        eachPortfolioModifyUseCase.updatePortfolio(ModifyEachPortfolioRequest.from(memberId, portFolioModifyApiRequest));

        return ResponseFactory.success("포트폴리오 수정 성공");
    }
}
