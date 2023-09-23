package hasix.junear.news.domain;

import hasix.junear.news.application.dto.RecentNewsResponse;

import java.util.List;

public interface NewsRepository {
    List<RecentNewsResponse> findRecentNews();

    List<RecentNewsResponse> findNewsByIndustryId(Long id);
}
