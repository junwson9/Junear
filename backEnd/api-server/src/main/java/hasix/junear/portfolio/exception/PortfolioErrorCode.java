package hasix.junear.portfolio.exception;

import hasix.junear.common.exception.ErrorCode;

public enum PortfolioErrorCode implements ErrorCode {

    NOT_FOUND_PORTFOLIO("해당 기업을 찾을 수 없습니다", "001", 404),
    ALREADY_EXIST_PORTFOLIO("이미 존재하는 기업입니다", "002", 409),
    ALREADY_OWN_PORTFOLIO("이미 생성된 포트폴리오가 존재합니다.", "003", 409);

    private final String message;
    private final String errorCode;
    private final int statusCode;


    PortfolioErrorCode(String message, String errorCode, int statusCode) {
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
