package github.veikkoroc.crowd.service.api;

import com.github.pagehelper.PageInfo;
import github.veikkoroc.crowd.entity.role.Role;

import java.util.List;

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

    /**
     * 保存角色
     * @param role
     */
    void saveRole(Role role);

    /**
     * 修改角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 批量删除、单条记录删除
     * @param roleIdList
     */
    void removeRole(List<Integer> roleIdList);

}
