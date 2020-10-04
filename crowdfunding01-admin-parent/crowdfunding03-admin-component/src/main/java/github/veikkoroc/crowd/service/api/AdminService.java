package github.veikkoroc.crowd.service.api;

import com.github.pagehelper.PageInfo;
import github.veikkoroc.crowd.entity.Admin;

import java.util.List;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 16:08
 */
public interface AdminService {
    /**
     * 保存管理员
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 查询所有管理员
     * @return
     */
    List<Admin> getAll();

    /**
     * 根据用户账号和密码获取用户对象
     * @param loginAcct
     * @param userPswd
     * @return
     */
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    /**
     * 关键字查询的分页
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 删除管理员
     * @param adminId
     * @return
     */
    int remove(Integer adminId);

    /**
     * 通过id查询admin
     * @param adminId
     * @return
     */
    Admin getAdminById(Integer adminId);

    /**
     * 更新Admin
     * @param admin
     */
    void updateAdmin(Admin admin);
}
