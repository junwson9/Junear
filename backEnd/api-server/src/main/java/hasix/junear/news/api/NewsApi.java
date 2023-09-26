package hasix.junear.news.api;

import hasix.junear.common.response.ResponseFactory;
import hasix.junear.news.api.dto.IndustryNewsApiResponse;
import hasix.junear.news.api.dto.RecentNewsApiResponse;
import hasix.junear.news.application.IndustryNewsSearchUseCase;
import hasix.junear.news.application.NewsDateFormatter;
import hasix.junear.news.application.RecentNewsSearchUseCase;
import hasix.junear.news.application.dto.IndustryNewsResponse;
import hasix.junear.news.application.dto.RecentNewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsApi {

    private final RecentNewsSearchUseCase recentNewsSearchUseCase;
    private final IndustryNewsSearchUseCase industryNewsSearchUseCase;
    private final NewsDateFormatter newsDateFormatter;

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentNews() {

        List<RecentNewsResponse> recentNewList = recentNewsSearchUseCase.getRecentNews();

        List<RecentNewsApiResponse> responseList = recentNewList.stream()
                .map(recentNewsResponse -> RecentNewsApiResponse.from(recentNewsResponse, newsDateFormatter.format(recentNewsResponse.getTimes())))
                .collect(Collectors.toList());

        return ResponseFactory.success("최신 뉴스 조회 성공", responseList);
    }

    @GetMapping()
    public ResponseEntity<?> getIndustryNews(@RequestParam("industry_id") Long industryId) {

        List<IndustryNewsResponse> industryNewsList = industryNewsSearchUseCase.getIndustryNews(industryId);

        List<IndustryNewsApiResponse> responseList = industryNewsList.stream()
                .map(industryNewsResponse -> IndustryNewsApiResponse.from(industryNewsResponse, newsDateFormatter.format(industryNewsResponse.getTimes())))
                .collect(Collectors.toList());

        return ResponseFactory.success("산업 뉴스 조회 성공", responseList);
    }
}
