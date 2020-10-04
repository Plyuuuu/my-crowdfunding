package github.veikkoroc.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import github.veikkoroc.crowd.constant.CrowdConstant;
import github.veikkoroc.crowd.entity.Admin;
import github.veikkoroc.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            //使用@RequestParam注解的defaultValue属性，
            @RequestParam(value = "keyword" ,defaultValue = "") String keyword,
            //默认是第一页
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            //pageSize默认值是7，每页显示7条
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            ModelMap modelMap

    ){
        //调用Service方法获取PageInfo对象
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);

        //将PageInfo对象存入模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);

        return "admin-page";
    }

    /**
     * 删除admin
     * @param adminId
     * @param pageNum
     * @param keyword
     * @return
     */
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword
            )
    {
        //执行删除
        adminService.remove(adminId);
        //页面跳转:重定向到/admin/get/page.html
        //同时为了保持原本所在的页面和查询词，再附加上pageNum、keyword
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("/admin/save.html")
    public String saveAdmin(Admin admin){
        adminService.saveAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }

    @RequestMapping("/admin/to/edit/page.html")
    public String toEditPage(
            @RequestParam("adminId") Integer adminId,

            ModelMap modelMap
            ){
        // 根据adminId查询Admin对象
        Admin admin = adminService.getAdminById(adminId);
        // 将admin对象存入模型
        modelMap.addAttribute("admin",admin);

        return "admin-edit";
    }

    @RequestMapping("/admin/update.html")
    public String update(Admin admin,@RequestParam("pageNum") Integer pageNum,@RequestParam("keyword") String keyword){

        adminService.updateAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }
}
