package com.lingzhi.crm.customer.service;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerCategory;

/**
 * 客户类别Service接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface ICustomerCategoryService 
{
    /**
     * 查询客户类别
     * 
     * @param id 客户类别ID
     * @return 客户类别
     */
    public CustomerCategory selectCustomerCategoryById(Long id);

    /**
     * 查询客户类别列表
     * 
     * @param customerCategory 客户类别
     * @return 客户类别集合
     */
    public List<CustomerCategory> selectCustomerCategoryList(CustomerCategory customerCategory);

    /**
     * 新增客户类别
     * 
     * @param customerCategory 客户类别
     * @return 结果
     */
    public int insertCustomerCategory(CustomerCategory customerCategory);

    /**
     * 修改客户类别
     * 
     * @param customerCategory 客户类别
     * @return 结果
     */
    public int updateCustomerCategory(CustomerCategory customerCategory);

    /**
     * 批量删除客户类别
     * 
     * @param ids 需要删除的客户类别ID
     * @return 结果
     */
    public int deleteCustomerCategoryByIds(Long[] ids);

    /**
     * 删除客户类别信息
     * 
     * @param id 客户类别ID
     * @return 结果
     */
    public int deleteCustomerCategoryById(Long id);
}
