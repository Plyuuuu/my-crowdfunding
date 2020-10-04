package github.veikkoroc.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import github.veikkoroc.crowd.constant.CrowdConstant;
import github.veikkoroc.crowd.entity.Admin;
import github.veikkoroc.crowd.entity.AdminExample;
import github.veikkoroc.crowd.exception.LoginAcctAlreadyInUserException;
import github.veikkoroc.crowd.exception.LoginAcctAlreadyInUserForUpdateException;
import github.veikkoroc.crowd.exception.LoginFailedException;
import github.veikkoroc.crowd.mapper.AdminMapper;
import github.veikkoroc.crowd.service.api.AdminService;
import github.veikkoroc.crowd.util.CrowdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 16:10
 */
@Slf4j
@Service(value = "adminServiceImpl")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        //1、密码加密
        String userPswd = admin.getUserPswd();
        userPswd = CrowdUtil.md5(userPswd);
        admin.setUserPswd(userPswd);

        //2、生成创建时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cruTime = simpleDateFormat.format(date);
        admin.setCreateTime(cruTime);

        //执行保存
        try{
            adminMapper.insert(admin);
        }catch (Exception e){
            e.printStackTrace();
            log.info("=====================异常全类名:[{}]",e.getClass().getName());

            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUserException(CrowdConstant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
        }

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

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 1.调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum,pageSize);

        // 2.执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);

        // 3.封装到PageInfo对象中
        return new PageInfo<>(list);
    }

    @Override
    public int remove(Integer adminId) {
        int res = adminMapper.deleteByPrimaryKey(adminId);
        return res;
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        // updateByPrimaryKeySelective表示有选择的更新，对null不更新
        try{
            adminMapper.updateByPrimaryKeySelective(admin);
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("=====================异常全类名:[{}]",e.getClass().getName());

            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUserForUpdateException(CrowdConstant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
        }

    }
}
