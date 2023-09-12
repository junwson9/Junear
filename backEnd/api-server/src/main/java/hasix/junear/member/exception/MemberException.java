package hasix.junear.member.exception;

import hasix.junear.common.exception.CustomException;
import hasix.junear.common.exception.ErrorCode;

public class MemberException extends CustomException {
    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
