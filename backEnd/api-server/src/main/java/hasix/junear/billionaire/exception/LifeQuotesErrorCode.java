package hasix.junear.billionaire.exception;

import hasix.junear.common.exception.ErrorCode;

public enum LifeQuotesErrorCode implements ErrorCode {
    NOT_FOUND_LIFE_QUOTES("해당 아이디로 명언을 찾을 수 없습니다", "BIL_001", 500),
    NOT_FOUND_TODAY_LIFE_QUOTES("오늘의 명언을 조회하지 못했습니다.", "BIL_002", 500);

    private final String message;
    private final String errorCode;
    private final int statusCode;

    LifeQuotesErrorCode(String message, String errorCode, int statusCode) {
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
