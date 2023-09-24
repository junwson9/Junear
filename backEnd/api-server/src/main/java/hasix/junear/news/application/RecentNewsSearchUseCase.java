package hasix.junear.news.application;

import hasix.junear.common.exception.CustomException;
import hasix.junear.news.application.dto.IndustryNewsResponse;
import hasix.junear.news.application.dto.RecentNewsResponse;
import hasix.junear.news.domain.NewsRepository;
import hasix.junear.news.exception.NewsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecentNewsSearchUseCase {

    private final NewsRepository newsRepository;

    public List<RecentNewsResponse> getRecentNews() {
        List<RecentNewsResponse> recentNewsList = newsRepository.findRecentNews();

        if (!hasNewsData(recentNewsList)) {
            throw new CustomException(NewsException.NOT_FOUND_RECENT_NEWS);
        }

        return recentNewsList;
    }

    private boolean hasNewsData(List<RecentNewsResponse> list) {
        return list.size() != 0;
    }
}
