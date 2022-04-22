package com.lingzhi.crm.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerLabelMapper;
import com.lingzhi.crm.customer.domain.CustomerLabel;
import com.lingzhi.crm.customer.service.ICustomerLabelService;

/**
 * 客户标签Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerLabelServiceImpl implements ICustomerLabelService 
{
    @Autowired
    private CustomerLabelMapper customerLabelMapper;

    /**
     * 查询客户标签
     * 
     * @param id 客户标签ID
     * @return 客户标签
     */
    @Override
    public CustomerLabel selectCustomerLabelById(Long id)
    {
        return customerLabelMapper.selectCustomerLabelById(id);
    }

    /**
     * 查询客户标签列表
     * 
     * @param customerLabel 客户标签
     * @return 客户标签
     */
    @Override
    public List<CustomerLabel> selectCustomerLabelList(CustomerLabel customerLabel)
    {
        return customerLabelMapper.selectCustomerLabelList(customerLabel);
    }

    /**
     * 新增客户标签
     * 
     * @param customerLabel 客户标签
     * @return 结果
     */
    @Override
    public int insertCustomerLabel(CustomerLabel customerLabel)
    {
        return customerLabelMapper.insertCustomerLabel(customerLabel);
    }

    /**
     * 修改客户标签
     * 
     * @param customerLabel 客户标签
     * @return 结果
     */
    @Override
    public int updateCustomerLabel(CustomerLabel customerLabel)
    {
        return customerLabelMapper.updateCustomerLabel(customerLabel);
    }

    /**
     * 批量删除客户标签
     * 
     * @param ids 需要删除的客户标签ID
     * @return 结果
     */
    @Override
    public int deleteCustomerLabelByIds(Long[] ids)
    {
        return customerLabelMapper.deleteCustomerLabelByIds(ids);
    }

    /**
     * 删除客户标签信息
     * 
     * @param id 客户标签ID
     * @return 结果
     */
    @Override
    public int deleteCustomerLabelById(Long id)
    {
        return customerLabelMapper.deleteCustomerLabelById(id);
    }
}
