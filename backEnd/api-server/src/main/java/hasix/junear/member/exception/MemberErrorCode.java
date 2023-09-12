package hasix.junear.member.exception;

import hasix.junear.common.exception.ErrorCode;

public enum MemberErrorCode implements ErrorCode {

    NOT_FOUND_MEMBER("해당 회원을 찾을 수 없습니다","001",404);
    private final String message;
    private final String errorCode;
    private final int statusCode;


    MemberErrorCode(String message, String errorCode, int statusCode) {
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
