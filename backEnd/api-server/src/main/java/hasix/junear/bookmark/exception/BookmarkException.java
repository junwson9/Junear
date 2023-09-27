package hasix.junear.bookmark.exception;

import hasix.junear.common.exception.ErrorCode;

public enum BookmarkException implements ErrorCode {
    NOT_FOUND_BOOKMARK("북마크를 찾을 수 없습니다", "BMK_001", 404),
    NOT_FOUND_CORPORATION("북마크로 등록할 기업을 찾을 수 없습니다", "BMK_002", 404),
    ALREADY_EXIST_BOOKMARK("이미 북마크한 기업입니다", "BMK_003", 409);

    private final String message;
    private final String errorCode;
    private final int statusCode;

    BookmarkException(String message, String errorCode, int statusCode) {
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
