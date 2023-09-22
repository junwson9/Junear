package hasix.junear.corporation.application;

import hasix.junear.corporation.application.dto.SearchCorporationKeywordRequest;
import hasix.junear.corporation.application.dto.SearchCorporationKeywordResponse;
import hasix.junear.corporation.domain.Corporation;
import hasix.junear.corporation.domain.CorporationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchCorporationKeyword {

    private final CorporationRepository corporationRepository;

    public List<SearchCorporationKeywordResponse> searchCorporation(
            SearchCorporationKeywordRequest request) {
        /*
        TODO
        키워드 구분하기: 기업 코드, 기업 이름 등
        예외처리
         */
        List<Corporation> corporationList = findContainedKeyword(request.getKeyword());

        return SearchCorporationKeywordResponse.from(corporationList);
    }

    private List<Corporation> findContainedKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }

        if (isCorporationCode(keyword)) return corporationRepository.findByCorporationCodeIsContaining(keyword);
        else if(isCorporationName(keyword)) return corporationRepository.findByNameIsContaining(keyword);
        return null;

    }

    private static boolean isCorporationCode(String keyword) {
        return keyword.matches("^[0-9]{1,6}$");
    }

    private boolean isCorporationName(String keyword) {
        return keyword.matches("^[a-zA-Z가-힣]*$");
    }

}
