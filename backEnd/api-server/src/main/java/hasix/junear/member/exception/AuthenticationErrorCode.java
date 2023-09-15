package hasix.junear.member.exception;

import hasix.junear.common.exception.ErrorCode;

public enum AuthenticationErrorCode implements ErrorCode {
    INVALID_JWT("유효하지 않는 jwt 입니다","AUTH_001",400),

    EXPIRED_JWT("만료된 jwt 입니다","AUTH_002",401);
    private final String message;
    private final String errorCode;
    private final int statusCode;

    AuthenticationErrorCode(String message, String errorCode, int statusCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
