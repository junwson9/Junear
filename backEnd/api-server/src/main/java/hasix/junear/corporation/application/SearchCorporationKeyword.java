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

        return findContainedKeyword(request.getKeyword());
    }

    private List<SearchCorporationKeywordResponse> findContainedKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }

        if (isCorporationCode(keyword)) return corporationRepository.findByCorporationCodeIsContaining(keyword);
        else return corporationRepository.findByNameIsContaining(keyword);

    }

    private static boolean isCorporationCode(String keyword) {
        return keyword.matches("^[0-9]{1,6}$");
    }

}
