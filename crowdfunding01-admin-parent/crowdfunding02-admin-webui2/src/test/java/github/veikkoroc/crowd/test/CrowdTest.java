package github.veikkoroc.crowd.test;

import github.veikkoroc.crowd.entity.Admin;
import github.veikkoroc.crowd.entity.role.Role;
import github.veikkoroc.crowd.mapper.AdminMapper;
import github.veikkoroc.crowd.mapper.RoleMapper;
import github.veikkoroc.crowd.service.api.AdminService;
import github.veikkoroc.crowd.service.api.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 14:28
 */

/**
 * 在类上标记必要的注解，Spring整合Junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//添加Spring配置文件
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    @Test
    public void testRoleService(){

        //roleService.saveRole(new Role(null,"迪丽热巴"));

        // 测试批量删 除
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        roleService.removeRole(list);

    }

    @Test
    public void testRoleMapper(){
        for (int i = 0; i < 271; i++) {
            Role role = new Role(null, "Role"+i);
            roleMapper.insert(role);
        }
    }

    @Test
    public void testTx(){
        Admin admin = new Admin(null, "Baby", "14562", "安其拉宝贝", "baby@aa.com", null);
        adminService.saveAdmin(admin);
    }

    @Test
    public void testLog(){
        //1.获取Logger对象,这里传入的Class对象就是当前打印日志的类
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        //2.根据不同日志级别打印日志
        logger.debug("Hello I am Debug!!!");
        logger.debug("Hello I am Debug!!!");
        logger.debug("Hello I am Debug!!!");

        logger.info("Info level!!!");
        logger.info("Info level!!!");
        logger.info("Info level!!!");

        logger.warn("Warn level!!!");
        logger.warn("Warn level!!!");
        logger.warn("Warn level!!!");

        logger.error("ERROR lever!!!");
        logger.error("ERROR lever!!!");
        logger.error("ERROR lever!!!");
    }


    @Test
    public void testInsertAdmin(){
        Admin tom = new Admin(null,"Tom","123","汤姆","tom@qq.com",null);
        int insert = adminMapper.insert(tom);

        //如果在实际开发中，所有在想查看数值地方使用sout打印，会给项目上线运行带来问题
        //sout本质上是IO操作，通常IO比较消耗性能，会影响项目的性能
        //即使上线前花时间删掉，可能遗漏，而且麻烦
        //使用日志系统，可以通过日志的级别控制信息打印
        System.err.println("受影响的行数:"+insert);
    }

    @Test
    public void testLimit(){
        for (int i = 0; i < 217; i++) {
            adminMapper.insert(new Admin(null,"Tom"+i,"123"+i,"汤姆"+i,"tom@qq.com"+i,null));
        }
    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}


