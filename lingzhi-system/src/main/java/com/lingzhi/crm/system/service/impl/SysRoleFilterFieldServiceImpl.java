package com.lingzhi.crm.system.service.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.lingzhi.crm.common.core.domain.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.system.mapper.SysRoleFilterFieldMapper;
import com.lingzhi.crm.system.domain.SysRoleFilterField;
import com.lingzhi.crm.system.service.ISysRoleFilterFieldService;

/**
 * 角色过滤字段权限Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Service
public class SysRoleFilterFieldServiceImpl implements ISysRoleFilterFieldService 
{
    @Autowired
    private SysRoleFilterFieldMapper sysRoleFilterFieldMapper;

    /**
     * 查询角色过滤字段权限
     * 
     * @param roleId 角色过滤字段权限ID
     * @return 角色过滤字段权限
     */
    @Override
    public SysRoleFilterField selectSysRoleFilterFieldById(Long roleId)
    {
        return sysRoleFilterFieldMapper.selectSysRoleFilterFieldById(roleId);
    }

    /**
     * 查询角色过滤字段权限列表
     * 
     * @param sysRoleFilterField 角色过滤字段权限
     * @return 角色过滤字段权限
     */
    @Override
    public List<SysRoleFilterField> selectSysRoleFilterFieldList(SysRoleFilterField sysRoleFilterField)
    {
        return sysRoleFilterFieldMapper.selectSysRoleFilterFieldList(sysRoleFilterField);
    }

    /**
     * 新增角色过滤字段权限
     * 
     * @param sysRoleFilterField 角色过滤字段权限
     * @return 结果
     */
    @Override
    public int insertSysRoleFilterField(SysRoleFilterField sysRoleFilterField)
    {
        return sysRoleFilterFieldMapper.insertSysRoleFilterField(sysRoleFilterField);
    }

    /**
     * 修改角色过滤字段权限
     * 
     * @param sysRoleFilterField 角色过滤字段权限
     * @return 结果
     */
    @Override
    public int updateSysRoleFilterField(SysRoleFilterField sysRoleFilterField)
    {
        return sysRoleFilterFieldMapper.updateSysRoleFilterField(sysRoleFilterField);
    }

    /**
     * 批量删除角色过滤字段权限
     * 
     * @param roleIds 需要删除的角色过滤字段权限ID
     * @return 结果
     */
    @Override
    public int deleteSysRoleFilterFieldByIds(Long[] roleIds)
    {
        return sysRoleFilterFieldMapper.deleteSysRoleFilterFieldByIds(roleIds);
    }

    /**
     * 删除角色过滤字段权限信息
     * 
     * @param roleId 角色过滤字段权限ID
     * @return 结果
     */
    @Override
    public int deleteSysRoleFilterFieldById(Long roleId)
    {
        return sysRoleFilterFieldMapper.deleteSysRoleFilterFieldById(roleId);
    }

    @Override
    public List<String> getFilterFieldNameListByRoleId(Long roleId) {
        if(roleId != null){
            return sysRoleFilterFieldMapper.getFilterFieldNameListByRoleId(roleId);
        }else {
            return new LinkedList<>();
        }

    }

    @Override
    public int insertRoleFilterField(SysRole role) {
        if(role.getTableField() != null && role.getTableField().length > 0){
            List<SysRoleFilterField> filterFieldList = new LinkedList<>();
            Arrays.asList(role.getTableField()).forEach(fieldName -> {
                SysRoleFilterField tempField = new SysRoleFilterField();
                tempField.setRoleId(role.getRoleId());
                tempField.setFieldName(fieldName);
                filterFieldList.add(tempField);
            });
            return sysRoleFilterFieldMapper.insertRoleFilterFieldBatch(filterFieldList);
        }
        return 0;
    }
}
