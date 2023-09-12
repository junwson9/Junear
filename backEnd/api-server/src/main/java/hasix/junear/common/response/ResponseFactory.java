package hasix.junear.common.response;


import hasix.junear.common.exception.ErrorCode;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {

    public static ResponseEntity<?> success(String message, Object data){
        SuccessResponseDto successResponse = new SuccessResponseDto(message, data);
        return ResponseEntity.status(200)
                .body(successResponse);
    }
    public static ResponseEntity<?> success(String message){
        // 값이 없을때는 빈 배열을 담는다.
        SuccessResponseDto successResponse = new SuccessResponseDto(message, new ArrayList<>());
        return ResponseEntity.status(200)
                             .body(successResponse);
    }

    public static ResponseEntity<?> fail(ErrorCode errorCode){
        FailResponseDto failResponse = new FailResponseDto(errorCode.getMessage(), errorCode.getErrorCode());
        return ResponseEntity.status(errorCode.getStatusCode())
                .body(failResponse);
    }

    public static ResponseEntity<?> fail(String message, String errorCode, int statusCode){
        FailResponseDto failResponse = new FailResponseDto(message, errorCode);
        return ResponseEntity.status(statusCode)
                .body(failResponse);
    }
}
