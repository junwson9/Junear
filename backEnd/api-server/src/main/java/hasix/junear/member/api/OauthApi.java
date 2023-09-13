package hasix.junear.member.api;

import hasix.junear.common.response.ResponseFactory;
import hasix.junear.member.application.dto.OauthLoginApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class OauthApi {


    @PostMapping("/api/member/oauth")
    public ResponseEntity<?> oauthLogin(@Validated @RequestBody OauthLoginApiRequest oauthLoginApiRequest){

        return ResponseFactory.success("");
    }
}
