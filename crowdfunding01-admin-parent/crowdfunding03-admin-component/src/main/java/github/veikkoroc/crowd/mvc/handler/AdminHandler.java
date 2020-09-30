package github.veikkoroc.crowd.mvc.handler;

import github.veikkoroc.crowd.constant.CrowdConstant;
import github.veikkoroc.crowd.entity.Admin;
import github.veikkoroc.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/30 16:57
 */
@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPswd") String userPswd,
            HttpSession session
    ){
        //调用service执行登录检查,如果能返回admin则说明登陆成功,如果账号密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);

        //将登录成功返回的admin对象存入Session域
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);

        //防止刷新网页再查一次数据库,这里使用重定向
        //return "admin-main";
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session){
        //强制Session失效
        session.invalidate();
        //去登录页面
        return "redirect:/admin/to/login/page.html";
    }

}
