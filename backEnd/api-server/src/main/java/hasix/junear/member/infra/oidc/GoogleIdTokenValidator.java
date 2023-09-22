package hasix.junear.member.infra.oidc;

import hasix.junear.common.exception.CommonErrorCode;
import hasix.junear.common.exception.CustomException;
import hasix.junear.member.application.OauthMemberInfo;
import hasix.junear.member.domain.OauthProvider;
import hasix.junear.member.infra.jwt.IdTokenResolver;
import hasix.junear.member.infra.oauth.GoogleOAuthProperty;
import hasix.junear.member.infra.oauth.GoogleOAuthProvider;
import hasix.junear.member.infra.oauth.dto.GoogleOpenSearchDocsResponse;
import hasix.junear.member.infra.repository.RedisCacheRepository;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class GoogleIdTokenValidator extends AbstractIdTokenValidator {

    private final GoogleOAuthProvider googleOAuthProvider;
    private final String GOOGLE_ID_KEY = "sub";
    private final String GOOGLE_NAME_KEY = "name";
    private final String GOOGLE_PROFILE_IMAGE_KEY = "picture";
    private final String OIDC_DOCS_CACHE_KEY = "google-open-docs";
    private final String GOOGLE_JWKS_CACHE_KEY = "google-jwks";
    private final RedisCacheRepository redisCacheRepository;


    public GoogleIdTokenValidator(GoogleOAuthProperty googleOAuthProperty,
            IdTokenResolver idTokenResolver, GoogleOAuthProvider googleOAuthProvider,
            RedisCacheRepository redisCacheRepository) {
        super(googleOAuthProperty.toIdTokenProperty(), idTokenResolver);
        this.googleOAuthProvider = googleOAuthProvider;
        this.redisCacheRepository = redisCacheRepository;
    }

    /* Google Public Key를 받는 로직
     *  1. https://accounts.google.com/.well-known/openid-configuration -> jwks_uri 값을 파싱해온다.
     *  2. jwks_uri 으로 API를 호출해서 Public Key에 대한 응답 값을 받는다
     *  1번과 2번의 모든 결과는 캐싱을 해야 한다.
     */
    @Override
    List<OidcPublicKey> getOIDCPublicKeys() {
        GoogleOpenSearchDocsResponse googleOpenSearchDocs = getOIDCDocs();
        return getPublicKeys(googleOpenSearchDocs.getJwks_uri());
    }

    private List<OidcPublicKey> getPublicKeys(String url){
        List<OidcPublicKey> oidcPublicKeys = redisCacheRepository.getOIDCPublicKeys(
                GOOGLE_JWKS_CACHE_KEY);
        if(oidcPublicKeys == null){
            List<OidcPublicKey> publicKeys = googleOAuthProvider.getPublcKeys(url);
            redisCacheRepository.savePublicKey(GOOGLE_JWKS_CACHE_KEY, publicKeys);
            oidcPublicKeys = publicKeys;
        }
        return oidcPublicKeys;
    }

    private GoogleOpenSearchDocsResponse getOIDCDocs(){
        GoogleOpenSearchDocsResponse oidcDocs = redisCacheRepository.getOidcDocs(OIDC_DOCS_CACHE_KEY);
        if(oidcDocs ==null){
            GoogleOpenSearchDocsResponse googleOpenSearchDocs = googleOAuthProvider.getGoogleOpenSearchDocs();
            redisCacheRepository.saveGoogleOidcDocs(OIDC_DOCS_CACHE_KEY, googleOpenSearchDocs);
            oidcDocs = googleOpenSearchDocs;
        }
        return oidcDocs;
    }

    @Override
    OauthMemberInfo extractMemberInfoFromPayload(Map<String, Object> payload) {
        String oauthId = (String) payload.get(GOOGLE_ID_KEY);
        String name = (String) payload.get(GOOGLE_NAME_KEY);
        String profileImage = (String) payload.get(GOOGLE_PROFILE_IMAGE_KEY);
        if (requireValueIsNull(oauthId, name, profileImage)) {
            throw new CustomException(CommonErrorCode.SERVER_ERROR);
        }
        ;

        return OauthMemberInfo.builder()
                              .oauthId(oauthId)
                              .name(name)
                              .profileImageUrl(profileImage)
                              .oauthProvider(OauthProvider.GOOGLE)
                              .build();
    }

    private boolean requireValueIsNull(String oauthId, String name, String profileImage) {
        return oauthId == null || name == null || profileImage == null;
    }
}
