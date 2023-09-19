package hasix.junear.member.infra.oauth;

import hasix.junear.member.application.OauthWithdrawHandler;
import hasix.junear.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OauthWithdrawHandlerImpl implements OauthWithdrawHandler {

    @Override
    public void withdraw(Member member) {

    }

}
