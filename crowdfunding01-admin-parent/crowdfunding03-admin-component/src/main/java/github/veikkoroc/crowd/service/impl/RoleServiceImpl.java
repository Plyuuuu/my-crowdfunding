package github.veikkoroc.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import github.veikkoroc.crowd.entity.role.Role;
import github.veikkoroc.crowd.mapper.RoleMapper;
import github.veikkoroc.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/10/5 10:15
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 开启分页
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {

        // 1、开启分页功能
        PageHelper.startPage(pageNum,pageSize);

        // 2、执行查询
        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);

        // 3、封装为PageInfo对象返回

        return new PageInfo<>(roles);
    }
}
