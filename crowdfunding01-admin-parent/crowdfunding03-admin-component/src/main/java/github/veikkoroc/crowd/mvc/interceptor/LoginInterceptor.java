package github.veikkoroc.crowd.mvc.interceptor;

import github.veikkoroc.crowd.constant.CrowdConstant;
import github.veikkoroc.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * 登录拦截器
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/10/1 10:30
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1.通过request对象获取Session对象
        HttpSession session = request.getSession();

        // 2.尝试从session中获取Admin对象
        Object object = session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);

        // 3.判断 Object对象是否为空
        if (object == null){
            // 4.没有登录，抛出资源禁止访问异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);

        }

        // 5.如果admin对象不为空,返回true放行

        return true;
    }
}
