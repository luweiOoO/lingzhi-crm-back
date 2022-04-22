package com.lingzhi.crm.customer.mapper;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerPublicGroup;

/**
 * 客户公海分组信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface CustomerPublicGroupMapper 
{
    /**
     * 查询客户公海分组信息
     * 
     * @param id 客户公海分组信息ID
     * @return 客户公海分组信息
     */
    public CustomerPublicGroup selectCustomerPublicGroupById(Long id);

    /**
     * 查询客户公海分组信息列表
     * 
     * @param customerPublicGroup 客户公海分组信息
     * @return 客户公海分组信息集合
     */
    public List<CustomerPublicGroup> selectCustomerPublicGroupList(CustomerPublicGroup customerPublicGroup);

    /**
     * 新增客户公海分组信息
     * 
     * @param customerPublicGroup 客户公海分组信息
     * @return 结果
     */
    public int insertCustomerPublicGroup(CustomerPublicGroup customerPublicGroup);

    /**
     * 修改客户公海分组信息
     * 
     * @param customerPublicGroup 客户公海分组信息
     * @return 结果
     */
    public int updateCustomerPublicGroup(CustomerPublicGroup customerPublicGroup);

    /**
     * 删除客户公海分组信息
     * 
     * @param id 客户公海分组信息ID
     * @return 结果
     */
    public int deleteCustomerPublicGroupById(Long id);

    /**
     * 批量删除客户公海分组信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerPublicGroupByIds(Long[] ids);
}
