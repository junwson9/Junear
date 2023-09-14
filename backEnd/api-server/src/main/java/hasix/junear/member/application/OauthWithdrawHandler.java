package hasix.junear.member.application;

import hasix.junear.member.domain.Member;

public interface OauthWithdrawHandler {
    void withdraw(Member member);
}
