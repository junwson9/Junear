package hasix.junear.corporation.api;

import hasix.junear.common.response.ResponseFactory;
import hasix.junear.corporation.api.dto.CorporationDetailsApiResponse;
import hasix.junear.corporation.api.dto.CorporationDetailsAuthenticatedApiResponse;
import hasix.junear.corporation.api.dto.CorporationSearchApiResponse;
import hasix.junear.corporation.application.SearchCorporationKeyword;
import hasix.junear.corporation.application.ViewCorporationAuthenticatedDetails;
import hasix.junear.corporation.application.ViewCorporationDetails;
import hasix.junear.corporation.application.dto.SearchCorporationKeywordRequest;
import hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsAuthenticatedRequest;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsRequest;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsAuthenticatedResponse;
import hasix.junear.springconfig.config.auth.AuthMember;
import hasix.junear.springconfig.config.auth.AuthenticatedMember;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/corporation" )
@RequiredArgsConstructor
public class CorporationApi {

    private final SearchCorporationKeyword searchCorporationKeyword;
    private final ViewCorporationDetails viewCorporationDetails;
    private final ViewCorporationAuthenticatedDetails viewCorporationAuthenticatedDetails;

    @GetMapping("/{corportaion_id}")
    public ResponseEntity<?> corporationDetails(@PathVariable("corportaion_id") Long id) {

        ViewCorporationDetailsResponse result = viewCorporationDetails.getCorporationDetails(
                ViewCorporationDetailsRequest.from(id));

        return ResponseFactory.success("기업 상세 조회 성공", CorporationDetailsApiResponse.from(result));
    }

    @GetMapping("/auth/{corportaion_id}")
    public ResponseEntity<?> corporationDetailsAuthenticated(@AuthenticatedMember AuthMember member, @PathVariable("corportaion_id") Long corporationId) {

        ViewCorporationDetailsAuthenticatedResponse result = viewCorporationAuthenticatedDetails.getCorporationDetails(
                ViewCorporationDetailsAuthenticatedRequest.from(member.getId(), corporationId));

        return ResponseFactory.success("기업 상세 조회 성공", CorporationDetailsAuthenticatedApiResponse.from(result));
    }

    @GetMapping("/search")
    public ResponseEntity<?> corporationSearch(@RequestParam(value = "keyword") String keyword) {

        List<SearchCorporationKeywordResponse> result = searchCorporationKeyword.searchCorporation(
                SearchCorporationKeywordRequest.from(keyword));

        if(result == null) return ResponseFactory.success("검색된 키워드가 없습니다.");
        return ResponseFactory.success("기업 키워드 검색 성공", CorporationSearchApiResponse.from(result));
    }

}
