package hasix.junear.portfolio.exception;

import hasix.junear.common.exception.CustomException;
import hasix.junear.common.exception.ErrorCode;

public class PortfolioException extends CustomException {
    public PortfolioException(ErrorCode errorCode) {
        super(errorCode);
    }

}
