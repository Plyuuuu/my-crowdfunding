package github.veikkoroc.crowd.service.api;

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
}
