package github.veikkoroc.test;

import github.veikkoroc.crowd.entity.role.Role;
import github.veikkoroc.crowd.service.impl.RoleServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/10/6 15:48
 */
@ComponentScan(basePackages = {"github.veikkoroc.crowd.service"})
public class RoleServiceImplTest {
    public static void main(String[] args) {





        RoleServiceImpl roleService = new RoleServiceImpl();
        roleService.saveRole(new Role(null,"迪丽热巴"));
        /*List<Integer> list = new ArrayList<>();
        list.add(0);
        roleService.removeRole(list);*/
    }
}
