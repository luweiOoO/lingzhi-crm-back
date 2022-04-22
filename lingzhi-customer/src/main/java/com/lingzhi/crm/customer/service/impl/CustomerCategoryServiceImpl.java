package com.lingzhi.crm.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerCategoryMapper;
import com.lingzhi.crm.customer.domain.CustomerCategory;
import com.lingzhi.crm.customer.service.ICustomerCategoryService;

/**
 * 客户类别Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerCategoryServiceImpl implements ICustomerCategoryService 
{
    @Autowired
    private CustomerCategoryMapper customerCategoryMapper;

    /**
     * 查询客户类别
     * 
     * @param id 客户类别ID
     * @return 客户类别
     */
    @Override
    public CustomerCategory selectCustomerCategoryById(Long id)
    {
        return customerCategoryMapper.selectCustomerCategoryById(id);
    }

    /**
     * 查询客户类别列表
     * 
     * @param customerCategory 客户类别
     * @return 客户类别
     */
    @Override
    public List<CustomerCategory> selectCustomerCategoryList(CustomerCategory customerCategory)
    {
        return customerCategoryMapper.selectCustomerCategoryList(customerCategory);
    }

    /**
     * 新增客户类别
     * 
     * @param customerCategory 客户类别
     * @return 结果
     */
    @Override
    public int insertCustomerCategory(CustomerCategory customerCategory)
    {
        return customerCategoryMapper.insertCustomerCategory(customerCategory);
    }

    /**
     * 修改客户类别
     * 
     * @param customerCategory 客户类别
     * @return 结果
     */
    @Override
    public int updateCustomerCategory(CustomerCategory customerCategory)
    {
        return customerCategoryMapper.updateCustomerCategory(customerCategory);
    }

    /**
     * 批量删除客户类别
     * 
     * @param ids 需要删除的客户类别ID
     * @return 结果
     */
    @Override
    public int deleteCustomerCategoryByIds(Long[] ids)
    {
        return customerCategoryMapper.deleteCustomerCategoryByIds(ids);
    }

    /**
     * 删除客户类别信息
     * 
     * @param id 客户类别ID
     * @return 结果
     */
    @Override
    public int deleteCustomerCategoryById(Long id)
    {
        return customerCategoryMapper.deleteCustomerCategoryById(id);
    }
}
