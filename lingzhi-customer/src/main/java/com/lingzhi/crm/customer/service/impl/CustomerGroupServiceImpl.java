package com.lingzhi.crm.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerGroupMapper;
import com.lingzhi.crm.customer.domain.CustomerGroup;
import com.lingzhi.crm.customer.service.ICustomerGroupService;

/**
 * 客户分组信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerGroupServiceImpl implements ICustomerGroupService 
{
    @Autowired
    private CustomerGroupMapper customerGroupMapper;

    /**
     * 查询客户分组信息
     * 
     * @param id 客户分组信息ID
     * @return 客户分组信息
     */
    @Override
    public CustomerGroup selectCustomerGroupById(Long id)
    {
        return customerGroupMapper.selectCustomerGroupById(id);
    }

    /**
     * 查询客户分组信息列表
     * 
     * @param customerGroup 客户分组信息
     * @return 客户分组信息
     */
    @Override
    public List<CustomerGroup> selectCustomerGroupList(CustomerGroup customerGroup)
    {
        return customerGroupMapper.selectCustomerGroupList(customerGroup);
    }

    /**
     * 新增客户分组信息
     * 
     * @param customerGroup 客户分组信息
     * @return 结果
     */
    @Override
    public int insertCustomerGroup(CustomerGroup customerGroup)
    {
        return customerGroupMapper.insertCustomerGroup(customerGroup);
    }

    /**
     * 修改客户分组信息
     * 
     * @param customerGroup 客户分组信息
     * @return 结果
     */
    @Override
    public int updateCustomerGroup(CustomerGroup customerGroup)
    {
        return customerGroupMapper.updateCustomerGroup(customerGroup);
    }

    /**
     * 批量删除客户分组信息
     * 
     * @param ids 需要删除的客户分组信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerGroupByIds(Long[] ids)
    {
        return customerGroupMapper.deleteCustomerGroupByIds(ids);
    }

    /**
     * 删除客户分组信息信息
     * 
     * @param id 客户分组信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerGroupById(Long id)
    {
        return customerGroupMapper.deleteCustomerGroupById(id);
    }
}
