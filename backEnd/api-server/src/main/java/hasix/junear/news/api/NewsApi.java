package hasix.junear.news.api;

import hasix.junear.common.response.ResponseFactory;
import hasix.junear.news.api.dto.RecentNewsApiResponse;
import hasix.junear.news.application.RecentNewsSearchUseCase;
import hasix.junear.news.application.dto.RecentNewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsApi {

    private final RecentNewsSearchUseCase recentNewsSearchUseCase;

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentNews() {

        List<RecentNewsResponse> recentNewList = recentNewsSearchUseCase.getRecentNews();

        List<RecentNewsApiResponse> responseList = recentNewList.stream()
                .map(RecentNewsApiResponse::from)
                .collect(Collectors.toList());

        return ResponseFactory.success("최신 뉴스 조회 성공", responseList);
    }

    @GetMapping
    public ResponseEntity<?> getIndustryNews(@PathVariable("industry_id") String industryId) {


        return ResponseFactory.success("산업 뉴스 조회 성공");
    }
}
