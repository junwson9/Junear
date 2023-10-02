package hasix.junear.corporation.domain;

public interface BookmarkChecker {

    boolean existsByCorporationIdAndMemberId(Long corporationId, Long memberId);

}
