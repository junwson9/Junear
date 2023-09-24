package hasix.junear.news.exception;

import hasix.junear.common.exception.ErrorCode;

public enum NewsException implements ErrorCode {
    NOT_FOUND_RECENT_NEWS("산업별 최신 뉴스를 찾을 수 없습니다", "NWS_001", 500),
    NOT_FOUND_INDUSTRY_NEWS("해당 산업 뉴스를 찾을 수 없습니다", "NWS_002", 500),
    NOT_FOUND_INDUSTRY("해당 산업 뉴스를 찾을 수 없습니다", "NWS_002", 500);

    private final String message;
    private final String errorCode;
    private final int statusCode;

    NewsException(String message, String errorCode, int statusCode) {
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
