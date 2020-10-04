package github.veikkoroc.crowd.exception;

/**
 *
 * 禁止访问资源异常
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/10/1 10:40
 */
public class AccessForbiddenException extends RuntimeException{
    private static final long serialVersionUID = -4800666499272269302L;

    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
