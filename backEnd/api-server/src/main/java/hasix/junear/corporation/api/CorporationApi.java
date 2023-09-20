package hasix.junear.corporation.api;

import hasix.junear.common.response.ResponseFactory;
import hasix.junear.corporation.api.dto.CorporationApiRequest;
import hasix.junear.corporation.api.dto.CorporationApiResponse;
import hasix.junear.corporation.application.SearchCorporation;
import hasix.junear.corporation.application.ViewCorporationDetails;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsRequest;
import hasix.junear.corporation.application.dto.ViewCorporationDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corporation" )
@RequiredArgsConstructor
public class CorporationApi {

    private final SearchCorporation searchCorporation;
    private final ViewCorporationDetails viewCorporationDetails;

    @GetMapping("/{corportaion_id}")
    public ResponseEntity<?> getCorporationDetails(@PathVariable("corportaion_id") Long id) {

        ViewCorporationDetailsResponse result = viewCorporationDetails.getCorporationDetails(id);
        /*
        TODO
        - bookmark 여부 제공
        - news list 제공
         */

        return ResponseFactory.success("기업 상세 조회 성공", CorporationApiResponse.from(result));
    }

//    @GetMapping("/{corportaion_id}")
//    public ResponseEntity<?> getCorporationDetails(@Validated CorporationApiRequest apiRequest) {
//
//        ViewCorporationDetailsResponse result = viewCorporationDetails.getCorporationDetails(apiRequest.to());
//        return ResponseFactory.success("기업 상세 조회 성공", CorporationApiResponse.from(result));
//    }

}
