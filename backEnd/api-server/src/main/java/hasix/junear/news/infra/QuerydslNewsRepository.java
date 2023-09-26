package hasix.junear.news.infra;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hasix.junear.industry.domain.QIndustry;
import hasix.junear.news.application.dto.IndustryNewsResponse;
import hasix.junear.news.application.dto.RecentNewsResponse;
import hasix.junear.news.domain.QNews;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuerydslNewsRepository {
    private final JPAQueryFactory queryFactory;

    public List<RecentNewsResponse> findRecentNews() {
        QIndustry industry = QIndustry.industry;
        QNews news = QNews.news;

        List<RecentNewsResponse> result = queryFactory
                .select(
                        Projections.constructor(
                                RecentNewsResponse.class,
                                industry.type.as("industry"),
                                news.title,
                                news.originUrl,
                                news.imageUrl,
                                news.times,
                                news.media
                        )
                )
                .from(news)
                .join(industry).on(news.industryId.eq(industry.id))
                .where(news.id.in(
                        queryFactory.select(news.id.max())
                                .from(news)
                                .groupBy(news.industryId)
                ))
                .fetch();

        return result;
    }

    public List<IndustryNewsResponse> findNewsByIndustryId(Long industryId) {
        QIndustry industry = QIndustry.industry;
        QNews news = QNews.news;

        List<IndustryNewsResponse> result = queryFactory
                .select(
                        Projections.constructor(
                                IndustryNewsResponse.class,
                                industry.type.as("industry"),
                                news.title,
                                news.originUrl,
                                news.imageUrl,
                                news.title,
                                news.media
                        )
                )
                .from(news)
                .join(industry).on(news.industryId.eq(industry.id))
                .where(news.industryId.eq(industryId))
                .fetch();

        return result;
    }
}
