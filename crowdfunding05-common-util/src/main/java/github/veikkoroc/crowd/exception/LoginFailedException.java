package github.veikkoroc.crowd.exception;

/**
 * 登录失败后抛出的异常
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/30 16:38
 */
public class LoginFailedException extends RuntimeException{

    private static final long serialVersionUID = -4869550660326162514L;

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    protected LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
