package com.lingzhi.crm.customer.mapper;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerGroup;

/**
 * 客户分组信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface CustomerGroupMapper 
{
    /**
     * 查询客户分组信息
     * 
     * @param id 客户分组信息ID
     * @return 客户分组信息
     */
    public CustomerGroup selectCustomerGroupById(Long id);

    /**
     * 查询客户分组信息列表
     * 
     * @param customerGroup 客户分组信息
     * @return 客户分组信息集合
     */
    public List<CustomerGroup> selectCustomerGroupList(CustomerGroup customerGroup);

    /**
     * 新增客户分组信息
     * 
     * @param customerGroup 客户分组信息
     * @return 结果
     */
    public int insertCustomerGroup(CustomerGroup customerGroup);

    /**
     * 修改客户分组信息
     * 
     * @param customerGroup 客户分组信息
     * @return 结果
     */
    public int updateCustomerGroup(CustomerGroup customerGroup);

    /**
     * 删除客户分组信息
     * 
     * @param id 客户分组信息ID
     * @return 结果
     */
    public int deleteCustomerGroupById(Long id);

    /**
     * 批量删除客户分组信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerGroupByIds(Long[] ids);
}
