package com.lingzhi.crm.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerSourceMapper;
import com.lingzhi.crm.customer.domain.CustomerSource;
import com.lingzhi.crm.customer.service.ICustomerSourceService;

/**
 * 客户来源信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerSourceServiceImpl implements ICustomerSourceService 
{
    @Autowired
    private CustomerSourceMapper customerSourceMapper;

    /**
     * 查询客户来源信息
     * 
     * @param id 客户来源信息ID
     * @return 客户来源信息
     */
    @Override
    public CustomerSource selectCustomerSourceById(Long id)
    {
        return customerSourceMapper.selectCustomerSourceById(id);
    }

    /**
     * 查询客户来源信息列表
     * 
     * @param customerSource 客户来源信息
     * @return 客户来源信息
     */
    @Override
    public List<CustomerSource> selectCustomerSourceList(CustomerSource customerSource)
    {
        return customerSourceMapper.selectCustomerSourceList(customerSource);
    }

    /**
     * 新增客户来源信息
     * 
     * @param customerSource 客户来源信息
     * @return 结果
     */
    @Override
    public int insertCustomerSource(CustomerSource customerSource)
    {
        return customerSourceMapper.insertCustomerSource(customerSource);
    }

    /**
     * 修改客户来源信息
     * 
     * @param customerSource 客户来源信息
     * @return 结果
     */
    @Override
    public int updateCustomerSource(CustomerSource customerSource)
    {
        return customerSourceMapper.updateCustomerSource(customerSource);
    }

    /**
     * 批量删除客户来源信息
     * 
     * @param ids 需要删除的客户来源信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerSourceByIds(Long[] ids)
    {
        return customerSourceMapper.deleteCustomerSourceByIds(ids);
    }

    /**
     * 删除客户来源信息信息
     * 
     * @param id 客户来源信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerSourceById(Long id)
    {
        return customerSourceMapper.deleteCustomerSourceById(id);
    }
}
