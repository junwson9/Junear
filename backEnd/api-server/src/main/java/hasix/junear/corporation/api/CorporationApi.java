package hasix.junear.corporation.api;

import hasix.junear.common.response.ResponseFactory;
import hasix.junear.corporation.api.dto.CorporationDetailsApiResponse;
import hasix.junear.corporation.api.dto.CorporationSearchApiResponse;
import hasix.junear.corporation.application.SearchCorporationKeyword;
import hasix.junear.corporation.application.ViewCorporationDetails;
import hasix.junear.corporation.application.dto.SearchCorporationKeywordRequest;
import hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsRequest;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corporation" )
@RequiredArgsConstructor
public class CorporationApi {

    private final SearchCorporationKeyword searchCorporationKeyword;
    private final ViewCorporationDetails viewCorporationDetails;

    @GetMapping("/{corportaion_id}")
    public ResponseEntity<?> corporationDetails(@PathVariable("corportaion_id") Long id) {

        ViewCorporationDetailsResponse result = viewCorporationDetails.getCorporationDetails(
                ViewCorporationDetailsRequest.from(id));
        /*
        TODO
        - bookmark 여부 제공
        - news list 제공
         */
        return ResponseFactory.success("기업 상세 조회 성공", CorporationDetailsApiResponse.from(result));
    }

    @GetMapping("/search")
    public ResponseEntity<?> corporationSearch(@RequestParam(value = "keyword") String keyword) {

        List<SearchCorporationKeywordResponse> result = searchCorporationKeyword.searchCorporation(
                SearchCorporationKeywordRequest.from(keyword));

        return ResponseFactory.success("기업 키워드 검색 성공", CorporationSearchApiResponse.from(result));
    }

}
