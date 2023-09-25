package hasix.junear.corporation.exception;

import hasix.junear.common.exception.CustomException;
import hasix.junear.common.exception.ErrorCode;

public class CorporationException extends CustomException {
    public CorporationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
