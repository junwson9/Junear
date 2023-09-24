package hasix.junear.news.application;

import hasix.junear.common.exception.CustomException;
import hasix.junear.news.application.dto.IndustryNewsResponse;
import hasix.junear.news.application.dto.RecentNewsResponse;
import hasix.junear.news.domain.NewsRepository;
import hasix.junear.news.exception.NewsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndustryNewsSearchUseCase {

    private final NewsRepository newsRepository;

    public List<IndustryNewsResponse> getIndustryNews(Long industryId) {

        // industry id 검증 로직 필요

        List<IndustryNewsResponse> industryNewsList = newsRepository.findNewsByIndustryId(industryId);

        if (!hasIndustryNewsData(industryNewsList)) {
            throw new CustomException(NewsException.NOT_FOUND_INDUSTRY_NEWS);
        }

        return industryNewsList;
    }

    private boolean hasIndustryNewsData(List<IndustryNewsResponse> list) {
        return list.size() != 0;
    }

}
