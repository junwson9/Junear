package hasix.junear.member.infra.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hasix.junear.common.exception.CommonErrorCode;
import hasix.junear.common.exception.CustomException;
import hasix.junear.member.infra.oauth.dto.GoogleOpenSearchDocsResponse;
import hasix.junear.member.infra.oidc.OidcPublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheRepository {

    private final RedisTemplate<String, String> redisStringTemplate;
    private final ValueOperations<String, String> operation;
    private final ObjectMapper mapper = new ObjectMapper();

    public RedisCacheRepository(RedisTemplate<String, String> redisStringTemplate) {
        this.redisStringTemplate = redisStringTemplate;
        this.operation = redisStringTemplate.opsForValue();
    }

    public void savePublicKey(String key, List<OidcPublicKey> publicKeys){
        try{
            String publicKeyString = mapper.writeValueAsString(publicKeys);
            operation.set(key, publicKeyString, 1, TimeUnit.DAYS);
        }catch (JsonProcessingException e){
            throw new CustomException(CommonErrorCode.SERVER_ERROR);
        }
    }

    public void saveGoogleOidcDocs(String key , GoogleOpenSearchDocsResponse oidcDocs){
        try{
            String oidcDocsString = mapper.writeValueAsString(oidcDocs);
            operation.set(key, oidcDocsString, 1, TimeUnit.DAYS);
        }catch (JsonProcessingException e){
            throw new CustomException(CommonErrorCode.SERVER_ERROR);
        }
    }

    public GoogleOpenSearchDocsResponse getOidcDocs(String key){
        String values = operation.get(key);
        if(values == null){
            return null;
        }
        try{
            return mapper.readValue(values, GoogleOpenSearchDocsResponse.class);
        }catch (JsonProcessingException e){
            throw new CustomException(CommonErrorCode.SERVER_ERROR);
        }
    }


    public List<OidcPublicKey> getOIDCPublicKeys(String key){
        String values = operation.get(key);
        if(values == null){
            return null;
        }
        try{
            return Arrays.asList(mapper.readValue(values, OidcPublicKey[].class));
        }catch (JsonProcessingException e){
            throw new CustomException(CommonErrorCode.SERVER_ERROR);
        }
    }
}
