package com.lingzhi.crm.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerStatusMapper;
import com.lingzhi.crm.customer.domain.CustomerStatus;
import com.lingzhi.crm.customer.service.ICustomerStatusService;

/**
 * 客户状态信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerStatusServiceImpl implements ICustomerStatusService 
{
    @Autowired
    private CustomerStatusMapper customerStatusMapper;

    /**
     * 查询客户状态信息
     * 
     * @param id 客户状态信息ID
     * @return 客户状态信息
     */
    @Override
    public CustomerStatus selectCustomerStatusById(Long id)
    {
        return customerStatusMapper.selectCustomerStatusById(id);
    }

    /**
     * 查询客户状态信息列表
     * 
     * @param customerStatus 客户状态信息
     * @return 客户状态信息
     */
    @Override
    public List<CustomerStatus> selectCustomerStatusList(CustomerStatus customerStatus)
    {
        return customerStatusMapper.selectCustomerStatusList(customerStatus);
    }

    /**
     * 新增客户状态信息
     * 
     * @param customerStatus 客户状态信息
     * @return 结果
     */
    @Override
    public int insertCustomerStatus(CustomerStatus customerStatus)
    {
        return customerStatusMapper.insertCustomerStatus(customerStatus);
    }

    /**
     * 修改客户状态信息
     * 
     * @param customerStatus 客户状态信息
     * @return 结果
     */
    @Override
    public int updateCustomerStatus(CustomerStatus customerStatus)
    {
        return customerStatusMapper.updateCustomerStatus(customerStatus);
    }

    /**
     * 批量删除客户状态信息
     * 
     * @param ids 需要删除的客户状态信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerStatusByIds(Long[] ids)
    {
        return customerStatusMapper.deleteCustomerStatusByIds(ids);
    }

    /**
     * 删除客户状态信息信息
     * 
     * @param id 客户状态信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerStatusById(Long id)
    {
        return customerStatusMapper.deleteCustomerStatusById(id);
    }
}
