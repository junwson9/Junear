package hasix.junear.billionaire.infra.repository;

import hasix.junear.billionaire.application.dto.BillionaireLifeQuotesResponse;
import hasix.junear.billionaire.domain.BillionaireRepository;

import java.util.List;

public class RedisBillionaireRepository implements BillionaireRepository {


    // redis에서 "todaysPhrases"키로 조회
    @Override
    public List<BillionaireLifeQuotesResponse> findById(List<Long> idList) {

        // redis 데이터 조회 실패 -> null값 리턴

        // redis 데이터 삽입 시 만료 시간 설정 -> 시간은 얼마나 세팅할 것인가

        return null;
    }


}
