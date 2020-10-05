package github.veikkoroc.crowd.mapper;


import java.util.List;

import github.veikkoroc.crowd.entity.role.Role;
import github.veikkoroc.crowd.entity.role.RoleExample;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 关键字查询Role
     * @param keyword
     * @return
     */
    List<Role> selectRoleByKeyword(String keyword);


}