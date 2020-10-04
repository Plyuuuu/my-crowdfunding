package github.veikkoroc.crowd.exception;

/**
 * 保存或者更新Admin时用户名重复发生异常
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/10/4 16:58
 */
public class LoginAcctAlreadyInUserException extends  RuntimeException {
    private static final long serialVersionUID = 6886307736417602811L;

    public LoginAcctAlreadyInUserException() {
    }

    public LoginAcctAlreadyInUserException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUserException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
