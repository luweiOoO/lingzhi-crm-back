package com.lingzhi.crm.customer.mapper;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerLabel;

/**
 * 客户标签Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface CustomerLabelMapper 
{
    /**
     * 查询客户标签
     * 
     * @param id 客户标签ID
     * @return 客户标签
     */
    public CustomerLabel selectCustomerLabelById(Long id);

    /**
     * 查询客户标签列表
     * 
     * @param customerLabel 客户标签
     * @return 客户标签集合
     */
    public List<CustomerLabel> selectCustomerLabelList(CustomerLabel customerLabel);

    /**
     * 新增客户标签
     * 
     * @param customerLabel 客户标签
     * @return 结果
     */
    public int insertCustomerLabel(CustomerLabel customerLabel);

    /**
     * 修改客户标签
     * 
     * @param customerLabel 客户标签
     * @return 结果
     */
    public int updateCustomerLabel(CustomerLabel customerLabel);

    /**
     * 删除客户标签
     * 
     * @param id 客户标签ID
     * @return 结果
     */
    public int deleteCustomerLabelById(Long id);

    /**
     * 批量删除客户标签
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerLabelByIds(Long[] ids);
}
