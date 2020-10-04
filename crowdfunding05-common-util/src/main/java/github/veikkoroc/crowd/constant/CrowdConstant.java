package github.veikkoroc.crowd.constant;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/30 9:45
 */
public class CrowdConstant {

    /**
     * 全局异常处理类的，存入ModelAndView的属性名称
     */
    public static final  String ATTR_NAME_EXCEPTION = "exception";

    /**
     * 登录失败
     */
    public static final String MESSAGE_LOGIN_FILED = "抱歉！账号或密码错误！";

    public static final String MESSAGE_LOGIN_ALREADY_IN_USE ="抱歉！这个账号已经被使用了！";

    public static final String MESSAGE_ACCESS_FORBIDDEN = "请登录再访问！";

    public static final String MESSAGE_STRING_INVALIDATE = "账号密码不能为空！";

    public static final String ATTR_NAME_LOGIN_ADMIN = "loginAdmin";

    public static final String MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE = "系统错误！账号不唯一！";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
}
