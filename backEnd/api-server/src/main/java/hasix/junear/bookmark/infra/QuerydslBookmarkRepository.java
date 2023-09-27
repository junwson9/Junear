package hasix.junear.bookmark.infra;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hasix.junear.bookmark.application.dto.BookmarkInfo;
import hasix.junear.bookmark.domain.QBookmark;
import hasix.junear.corporation.domain.QCorporation;
import hasix.junear.industry.domain.QIndustry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuerydslBookmarkRepository {
    private final JPAQueryFactory queryFactory;

    public List<BookmarkInfo> searchBookmarkByMemberIdAndIndustryIds(Long memberId, List<Long> ids) {
        QBookmark bookmark = QBookmark.bookmark;
        QCorporation corporation = QCorporation.corporation;
        QIndustry industry = QIndustry.industry;

        JPAQuery<BookmarkInfo> query = queryFactory
                .select(
                        Projections.constructor(
                                BookmarkInfo.class,
                                corporation.id.as("corporationId"),
                                corporation.name.as("corporationName"),
                                industry.type.as("industry")
                        )
                )
                .from(bookmark)
                .join(corporation).on(bookmark.corporationId.eq(corporation.id))
                .join(industry).on(corporation.industryId.eq(industry.id))
                .where(bookmark.memberId.eq(memberId));

        if (ids != null) {
            query.where(industry.id.in(ids));
        }

        List<BookmarkInfo> result = query.fetch();

        return result;
    }

}
