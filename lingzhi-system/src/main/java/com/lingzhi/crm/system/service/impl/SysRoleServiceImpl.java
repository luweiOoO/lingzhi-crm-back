package com.lingzhi.crm.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.lingzhi.crm.common.core.domain.DateFieldResultVo;
import com.lingzhi.crm.common.core.domain.TreeStringSelect;
import com.lingzhi.crm.common.core.domain.entity.SysDictData;
import com.lingzhi.crm.system.domain.vo.DictTypeAndDataListVo;
import com.lingzhi.crm.system.service.ISysDictDataService;
import com.lingzhi.crm.system.service.ISysRoleFilterFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingzhi.crm.common.constant.UserConstants;
import com.lingzhi.crm.common.core.domain.entity.SysRole;
import com.lingzhi.crm.common.exception.CustomException;
import com.lingzhi.crm.common.utils.StringUtils;
import com.lingzhi.crm.common.utils.spring.SpringUtils;
import com.lingzhi.crm.system.domain.SysRoleDept;
import com.lingzhi.crm.system.domain.SysRoleMenu;
import com.lingzhi.crm.system.mapper.SysRoleDeptMapper;
import com.lingzhi.crm.system.mapper.SysRoleMapper;
import com.lingzhi.crm.system.mapper.SysRoleMenuMapper;
import com.lingzhi.crm.system.mapper.SysUserRoleMapper;
import com.lingzhi.crm.system.service.ISysRoleService;

/**
 * 角色 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService
{
    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleDeptMapper roleDeptMapper;

    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private ISysRoleFilterFieldService roleFilterFieldService;

    //需展示的数据库字典类型
    private static final String tableSchemaNameDictType = "table_schema_name";
    //需展示的数据表字典类型
    private static final String findTableNameDictType = "find_table_name";
    //需过滤的表字段字典类型
    private static final String filterTableFieldNameDictType = "filter_table_field_name";


    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
//    @DataScope(deptAlias = "d")
    public List<SysRole> selectRoleList(SysRole role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId)
    {
        List<SysRole> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRoleAll()
    {
        return SpringUtils.getAopProxy(this).selectRoleList(new SysRole());
    }

    /**
     * 根据用户ID获取角色选择框列表
     * 
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    @Override
    public List<Integer> selectRoleListByUserId(Long userId)
    {
        return roleMapper.selectRoleListByUserId(userId);
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public SysRole selectRoleById(Long roleId)
    {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(SysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(SysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色是否允许操作
     * 
     * @param role 角色信息
     */
    @Override
    public void checkRoleAllowed(SysRole role)
    {
        if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin())
        {
            throw new CustomException("不允许操作超级管理员角色");
        }
    }

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId)
    {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertRole(SysRole role)
    {
        // 新增角色信息
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateRole(SysRole role)
    {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     * 修改角色状态
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRoleStatus(SysRole role)
    {
        return roleMapper.updateRole(role);
    }

    /**
     * 修改数据权限信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int authDataScope(SysRole role)
    {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        // 新增角色和部门信息（数据权限）
        insertRoleDept(role);
        // 删除角色与过滤字段关联
        roleFilterFieldService.deleteSysRoleFilterFieldById(role.getRoleId());
        // 新增角色与过滤字段信息 (数据权限)
        roleFilterFieldService.insertRoleFilterField(role);
        return 1;
    }

    /**
     * 新增角色菜单信息
     * 
     * @param role 角色对象
     */
    public int insertRoleMenu(SysRole role)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (Long menuId : role.getMenuIds())
        {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleDept(SysRole role)
    {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<SysRoleDept> list = new ArrayList<SysRoleDept>();
        for (Long deptId : role.getDeptIds())
        {
            SysRoleDept rd = new SysRoleDept();
            rd.setRoleId(role.getRoleId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0)
        {
            rows = roleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteRoleById(Long roleId)
    {
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(roleId);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(roleId);
        return roleMapper.deleteRoleById(roleId);
    }

    /**
     * 批量删除角色信息
     * 
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteRoleByIds(Long[] roleIds)
    {
        for (Long roleId : roleIds)
        {
            checkRoleAllowed(new SysRole(roleId));
            SysRole role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new CustomException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenu(roleIds);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDept(roleIds);
        return roleMapper.deleteRoleByIds(roleIds);
    }

    @Override
    public List<TreeStringSelect> getDataTableList(String tableSchemaName,List<String> dateTableNameList,List<String> filterFieldNameList) throws Exception {
        //查询字典中的数据库名,数据表列表,过滤的数据字段列表
        List<String> dictTypeList = Arrays.asList(tableSchemaNameDictType,findTableNameDictType,filterTableFieldNameDictType);
        List<DictTypeAndDataListVo> dictTypeAndDataListVoList = dictDataService.selectDictDataByTypeList(dictTypeList);
        Map<String,DictTypeAndDataListVo> dictDataMap = dictTypeAndDataListVoList.stream()
                .collect(Collectors.toMap(DictTypeAndDataListVo::getDictType, v -> v));
        tableSchemaName = "";
        dateTableNameList = new LinkedList<>();
        filterFieldNameList = new LinkedList<>();
        if(checkDictDataMap(dictDataMap,tableSchemaNameDictType)
                && checkDictDataMap(dictDataMap,findTableNameDictType)){
            tableSchemaName = dictDataMap.get(tableSchemaNameDictType).getSysDictDataList().get(0).getDictValue();
            dateTableNameList = dictDataMap.get(findTableNameDictType).getSysDictDataList().stream().map(SysDictData::getDictValue).distinct().collect(Collectors.toList());
        }
        if(checkDictDataMap(dictDataMap,filterTableFieldNameDictType)){
            filterFieldNameList = dictDataMap.get(filterTableFieldNameDictType).getSysDictDataList().stream().map(SysDictData::getDictValue).distinct().collect(Collectors.toList());
        }
        if(StringUtils.isEmpty(tableSchemaName) || dateTableNameList.size() < 1){
            throw new Exception("需查询的数据库和数据表为空");
        }
        //
        List<TreeStringSelect> treeList = new LinkedList<>();
        //查询出需展示的数据表字段信息
        List<DateFieldResultVo> dateFieldResultVoList = roleMapper.selectDateTableList(tableSchemaName, dateTableNameList, filterFieldNameList);
        if(dateFieldResultVoList != null && dateFieldResultVoList.size() > 0){
            Map<String,List<DateFieldResultVo>> fieldResultMap = dateFieldResultVoList.stream().collect(Collectors.groupingBy(DateFieldResultVo::getTableName));
            fieldResultMap.entrySet().forEach(fieldResult -> {
                TreeStringSelect tempTree = new TreeStringSelect(fieldResult);
                treeList.add(tempTree);
            });
        }
        return treeList;
    }

    /**
     * 校验指定字典类型是否在map中
     * @param typeAndDataListMap
     * @param dictType
     * @return
     */
    private boolean checkDictDataMap(Map<String,DictTypeAndDataListVo> typeAndDataListMap,String dictType){
        if(typeAndDataListMap.containsKey(dictType)
                && typeAndDataListMap.get(dictType).getSysDictDataList() != null
                && typeAndDataListMap.get(dictType).getSysDictDataList().size() > 0 ){
            return true;
        }
        return false;
    }
}
