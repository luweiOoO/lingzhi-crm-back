package com.lingzhi.crm.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.system.mapper.SysTenantMapper;
import com.lingzhi.crm.system.domain.SysTenant;
import com.lingzhi.crm.system.service.ISysTenantService;

/**
 * 租户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-10
 */
@Service
public class SysTenantServiceImpl implements ISysTenantService 
{
    @Autowired
    private SysTenantMapper sysTenantMapper;

    /**
     * 查询租户信息
     * 
     * @param tenantId 租户信息ID
     * @return 租户信息
     */
    @Override
    public SysTenant selectSysTenantById(Long tenantId)
    {
        return sysTenantMapper.selectSysTenantById(tenantId);
    }

    /**
     * 查询租户信息列表
     * 
     * @param sysTenant 租户信息
     * @return 租户信息
     */
    @Override
    public List<SysTenant> selectSysTenantList(SysTenant sysTenant)
    {
        return sysTenantMapper.selectSysTenantList(sysTenant);
    }

    /**
     * 新增租户信息
     * 
     * @param sysTenant 租户信息
     * @return 结果
     */
    @Override
    public int insertSysTenant(SysTenant sysTenant)
    {
        return sysTenantMapper.insertSysTenant(sysTenant);
    }

    /**
     * 修改租户信息
     * 
     * @param sysTenant 租户信息
     * @return 结果
     */
    @Override
    public int updateSysTenant(SysTenant sysTenant)
    {
        return sysTenantMapper.updateSysTenant(sysTenant);
    }

    /**
     * 批量删除租户信息
     * 
     * @param tenantIds 需要删除的租户信息ID
     * @return 结果
     */
    @Override
    public int deleteSysTenantByIds(Long[] tenantIds)
    {
        return sysTenantMapper.deleteSysTenantByIds(tenantIds);
    }

    /**
     * 删除租户信息信息
     * 
     * @param tenantId 租户信息ID
     * @return 结果
     */
    @Override
    public int deleteSysTenantById(Long tenantId)
    {
        return sysTenantMapper.deleteSysTenantById(tenantId);
    }
}
