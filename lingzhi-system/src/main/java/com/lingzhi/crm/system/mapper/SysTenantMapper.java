package com.lingzhi.crm.system.mapper;

import java.util.List;
import com.lingzhi.crm.system.domain.SysTenant;

/**
 * 租户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-10
 */
public interface SysTenantMapper 
{
    /**
     * 查询租户信息
     * 
     * @param tenantId 租户信息ID
     * @return 租户信息
     */
    public SysTenant selectSysTenantById(Long tenantId);

    /**
     * 查询租户信息列表
     * 
     * @param sysTenant 租户信息
     * @return 租户信息集合
     */
    public List<SysTenant> selectSysTenantList(SysTenant sysTenant);

    /**
     * 新增租户信息
     * 
     * @param sysTenant 租户信息
     * @return 结果
     */
    public int insertSysTenant(SysTenant sysTenant);

    /**
     * 修改租户信息
     * 
     * @param sysTenant 租户信息
     * @return 结果
     */
    public int updateSysTenant(SysTenant sysTenant);

    /**
     * 删除租户信息
     * 
     * @param tenantId 租户信息ID
     * @return 结果
     */
    public int deleteSysTenantById(Long tenantId);

    /**
     * 批量删除租户信息
     * 
     * @param tenantIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysTenantByIds(Long[] tenantIds);
}
