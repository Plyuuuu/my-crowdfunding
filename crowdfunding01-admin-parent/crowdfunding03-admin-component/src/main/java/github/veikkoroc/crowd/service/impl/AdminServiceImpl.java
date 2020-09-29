package github.veikkoroc.crowd.service.impl;

import github.veikkoroc.crowd.entity.Admin;
import github.veikkoroc.crowd.entity.AdminExample;
import github.veikkoroc.crowd.mapper.AdminMapper;
import github.veikkoroc.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 16:10
 */
@Service(value = "adminServiceImpl")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        //不带任何条件就是查询全部
        return adminMapper.selectByExample(new AdminExample());
    }
}
