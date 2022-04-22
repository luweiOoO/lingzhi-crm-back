package com.lingzhi.crm.system.service;

import java.util.List;

import com.lingzhi.crm.common.core.domain.entity.SysRole;
import com.lingzhi.crm.system.domain.SysRoleFilterField;

/**
 * 角色过滤字段权限Service接口
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
public interface ISysRoleFilterFieldService 
{
    /**
     * 查询角色过滤字段权限
     * 
     * @param roleId 角色过滤字段权限ID
     * @return 角色过滤字段权限
     */
    public SysRoleFilterField selectSysRoleFilterFieldById(Long roleId);

    /**
     * 查询角色过滤字段权限列表
     * 
     * @param sysRoleFilterField 角色过滤字段权限
     * @return 角色过滤字段权限集合
     */
    public List<SysRoleFilterField> selectSysRoleFilterFieldList(SysRoleFilterField sysRoleFilterField);

    /**
     * 新增角色过滤字段权限
     * 
     * @param sysRoleFilterField 角色过滤字段权限
     * @return 结果
     */
    public int insertSysRoleFilterField(SysRoleFilterField sysRoleFilterField);

    /**
     * 修改角色过滤字段权限
     * 
     * @param sysRoleFilterField 角色过滤字段权限
     * @return 结果
     */
    public int updateSysRoleFilterField(SysRoleFilterField sysRoleFilterField);

    /**
     * 批量删除角色过滤字段权限
     * 
     * @param roleIds 需要删除的角色过滤字段权限ID
     * @return 结果
     */
    public int deleteSysRoleFilterFieldByIds(Long[] roleIds);

    /**
     * 删除角色过滤字段权限信息
     * 
     * @param roleId 角色过滤字段权限ID
     * @return 结果
     */
    public int deleteSysRoleFilterFieldById(Long roleId);

    /**
     * 查询指定角色的过滤数据表字段列表
     * @param roleId
     * @return
     */
    public List<String> getFilterFieldNameListByRoleId(Long roleId);

    /**
     * 批量新增角色与过滤字段关系
     * @param role
     * @return
     */
    public int insertRoleFilterField(SysRole role);
}
