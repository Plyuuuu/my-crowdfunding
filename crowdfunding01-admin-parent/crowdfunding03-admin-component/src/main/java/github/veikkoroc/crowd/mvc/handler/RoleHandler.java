package github.veikkoroc.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import github.veikkoroc.crowd.entity.role.Role;
import github.veikkoroc.crowd.service.api.RoleService;
import github.veikkoroc.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/10/5 10:17
 */
@Controller
public class RoleHandler {
    @Autowired
    private RoleService roleService;

    /**
     * 没写@ResponseBody 会把 role/get/page/info 拿去拼接前后缀
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(value = "keyword" , defaultValue = "") String keyword

    ){
        //调用Service方法获取分页数据
        try {
            PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);

            // 封装到ResultEntity对象中返回
            return ResultEntity.successWithData(pageInfo);

        } catch (Exception e) {

            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }


    }

}
