package hasix.junear.news.infra;

import hasix.junear.news.application.dto.IndustryNewsResponse;
import hasix.junear.news.application.dto.RecentNewsResponse;
import hasix.junear.news.domain.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {

    private final QuerydslNewsRepository querydslNewsRepository;

    @Override
    public List<RecentNewsResponse> findRecentNews() {
        return querydslNewsRepository.findRecentNews();
    }

    @Override
    public List<IndustryNewsResponse> findNewsByIndustryId(Long industryId) {

        return querydslNewsRepository.findNewsByIndustryId(industryId);
    }
}
