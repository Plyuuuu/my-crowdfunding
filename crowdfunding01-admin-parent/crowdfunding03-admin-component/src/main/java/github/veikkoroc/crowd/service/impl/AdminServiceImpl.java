package github.veikkoroc.crowd.service.impl;

import github.veikkoroc.crowd.constant.CrowdConstant;
import github.veikkoroc.crowd.entity.Admin;
import github.veikkoroc.crowd.entity.AdminExample;
import github.veikkoroc.crowd.exception.LoginFailedException;
import github.veikkoroc.crowd.mapper.AdminMapper;
import github.veikkoroc.crowd.service.api.AdminService;
import github.veikkoroc.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {

        // 1、根据登录账号查询Admin对象

        // a.创建AdminExample对象,写了很多带SQL的方法
        AdminExample adminExample = new AdminExample();

        // b.创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();

        // c.在Criteria（标准）中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);

        // d.调用AdminMapper的方法执行查询
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        // 2、判断Admin对象是否为空
        if (admins == null || admins.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FILED);
        }
        if (admins.size()>1){
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }


        // 3、如果Admin对象为null则抛出异常

        Admin admin = admins.get(0);
        if (admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FILED);
        }

        // 4、如果Admin对象不为空,则将数据库密码从Admin对象中取出来

        String userPswdFromDB = admin.getUserPswd();

        // 5、将表提交的明文密码加密

        String userPswdForm = CrowdUtil.md5(userPswd);


        // 6、对密码进行比较

        if (!Objects.equals(userPswdFromDB,userPswdForm))  {
            // 7、如果比较结果不一致则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FILED);

        }

        // 8、如果一直则返回Admin对象

        return admin;
    }
}
