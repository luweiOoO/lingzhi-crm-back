package com.lingzhi.crm.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerPublicGroupMapper;
import com.lingzhi.crm.customer.domain.CustomerPublicGroup;
import com.lingzhi.crm.customer.service.ICustomerPublicGroupService;

/**
 * 客户公海分组信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerPublicGroupServiceImpl implements ICustomerPublicGroupService 
{
    @Autowired
    private CustomerPublicGroupMapper customerPublicGroupMapper;

    /**
     * 查询客户公海分组信息
     * 
     * @param id 客户公海分组信息ID
     * @return 客户公海分组信息
     */
    @Override
    public CustomerPublicGroup selectCustomerPublicGroupById(Long id)
    {
        return customerPublicGroupMapper.selectCustomerPublicGroupById(id);
    }

    /**
     * 查询客户公海分组信息列表
     * 
     * @param customerPublicGroup 客户公海分组信息
     * @return 客户公海分组信息
     */
    @Override
    public List<CustomerPublicGroup> selectCustomerPublicGroupList(CustomerPublicGroup customerPublicGroup)
    {
        return customerPublicGroupMapper.selectCustomerPublicGroupList(customerPublicGroup);
    }

    /**
     * 新增客户公海分组信息
     * 
     * @param customerPublicGroup 客户公海分组信息
     * @return 结果
     */
    @Override
    public int insertCustomerPublicGroup(CustomerPublicGroup customerPublicGroup)
    {
        return customerPublicGroupMapper.insertCustomerPublicGroup(customerPublicGroup);
    }

    /**
     * 修改客户公海分组信息
     * 
     * @param customerPublicGroup 客户公海分组信息
     * @return 结果
     */
    @Override
    public int updateCustomerPublicGroup(CustomerPublicGroup customerPublicGroup)
    {
        return customerPublicGroupMapper.updateCustomerPublicGroup(customerPublicGroup);
    }

    /**
     * 批量删除客户公海分组信息
     * 
     * @param ids 需要删除的客户公海分组信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerPublicGroupByIds(Long[] ids)
    {
        return customerPublicGroupMapper.deleteCustomerPublicGroupByIds(ids);
    }

    /**
     * 删除客户公海分组信息信息
     * 
     * @param id 客户公海分组信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerPublicGroupById(Long id)
    {
        return customerPublicGroupMapper.deleteCustomerPublicGroupById(id);
    }
}
