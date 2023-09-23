package hasix.junear.billionaire.infra.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hasix.junear.billionaire.application.dto.TodayLifeQuotes;
import hasix.junear.billionaire.domain.QBillionaire;
import hasix.junear.billionaire.domain.QLifeQuotes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuerydslBillionaireRepository {
    private final JPAQueryFactory queryFactory;

    public List<TodayLifeQuotes> findBillionaireLifeQuotesByIds(List<Long> ids) {
        QBillionaire billionaire = QBillionaire.billionaire;
        QLifeQuotes lifeQuotes = QLifeQuotes.lifeQuotes;
        log.info("ids : {}", ids);

        List<TodayLifeQuotes> result = queryFactory
                .select(
                        Projections.constructor(
                                TodayLifeQuotes.class,
                                billionaire.name,
                                billionaire.imageUrl,
                                lifeQuotes.phrase
                        )
                )
                .from(billionaire)
                .join(lifeQuotes).on(billionaire.id.eq(lifeQuotes.billionaireId))
                .where(lifeQuotes.id.in(ids))
                .fetch();

        for (TodayLifeQuotes todayLifeQuotes : result) {
            log.info("{}",todayLifeQuotes);
        }

        return result;
    }
}
