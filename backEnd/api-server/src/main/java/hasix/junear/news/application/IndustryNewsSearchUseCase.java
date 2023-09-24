package hasix.junear.news.application;

import hasix.junear.news.application.dto.IndustryNewsResponse;
import hasix.junear.news.application.dto.RecentNewsResponse;
import hasix.junear.news.domain.NewsRepository;
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

        List<IndustryNewsResponse> IndustryNewsList = newsRepository.findNewsByIndustryId(industryId);
        return null;
    }

}
