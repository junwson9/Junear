package hasix.junear.corporation.exception;

import hasix.junear.common.exception.ErrorCode;

public enum CorporationErrorCode implements ErrorCode {

    NOT_FOUND_CORPORATION("해당 기업을 찾을 수 없습니다", "001", 404);
    private final String message;
    private final String errorCode;
    private final int statusCode;


    CorporationErrorCode(String message, String errorCode, int statusCode) {
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
