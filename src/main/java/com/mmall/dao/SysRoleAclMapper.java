package com.mmall.dao;

import com.mmall.model.SysRoleAcl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleAclMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleAcl record);

    int insertSelective(SysRoleAcl record);

    SysRoleAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleAcl record);

    int updateByPrimaryKey(SysRoleAcl record);

    /**
     * 根据角色id列表，获取相应权限点id列表
     * @param roleIdList
     * @return
     */
    List<Integer> getAclIdListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);

    void deleteByRoleId(@Param("roleId") int roleId);

    /**
     * 批量更新
     * @param roleAclList
     */
    void batchInsert(@Param("roleAclList") List<SysRoleAcl> roleAclList);

    List<Integer> getRoleIdListByAclId(@Param("aclId") int aclId);
}