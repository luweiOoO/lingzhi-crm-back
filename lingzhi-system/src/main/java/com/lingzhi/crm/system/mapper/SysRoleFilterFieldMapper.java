package com.lingzhi.crm.system.mapper;

import java.util.List;
import com.lingzhi.crm.system.domain.SysRoleFilterField;
import org.apache.ibatis.annotations.Param;

/**
 * 角色过滤字段权限Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
public interface SysRoleFilterFieldMapper 
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
     * 删除角色过滤字段权限
     * 
     * @param roleId 角色过滤字段权限ID
     * @return 结果
     */
    public int deleteSysRoleFilterFieldById(Long roleId);

    /**
     * 批量删除角色过滤字段权限
     * 
     * @param roleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysRoleFilterFieldByIds(Long[] roleIds);

    /**
     * 根据角色id查询该角色需过滤的数据表字段列表
     * @param roleId
     * @return
     */
    public List<String> getFilterFieldNameListByRoleId(@Param("roleId") Long roleId);

    /**
     * 批量新增角色与过滤字段
     * @param filterFieldList
     * @return
     */
    public int insertRoleFilterFieldBatch(@Param("filterFieldList") List<SysRoleFilterField> filterFieldList);
}
