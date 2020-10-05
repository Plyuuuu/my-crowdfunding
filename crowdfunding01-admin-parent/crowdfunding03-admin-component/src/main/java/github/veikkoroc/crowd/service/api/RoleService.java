package github.veikkoroc.crowd.service.api;

import com.github.pagehelper.PageInfo;
import github.veikkoroc.crowd.entity.role.Role;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/10/5 10:14
 */
public interface RoleService {
    /**
     * 获取分页的信息
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

}
