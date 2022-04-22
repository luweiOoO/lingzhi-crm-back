package com.lingzhi.crm.customer.service;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerStatus;

/**
 * 客户状态信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface ICustomerStatusService 
{
    /**
     * 查询客户状态信息
     * 
     * @param id 客户状态信息ID
     * @return 客户状态信息
     */
    public CustomerStatus selectCustomerStatusById(Long id);

    /**
     * 查询客户状态信息列表
     * 
     * @param customerStatus 客户状态信息
     * @return 客户状态信息集合
     */
    public List<CustomerStatus> selectCustomerStatusList(CustomerStatus customerStatus);

    /**
     * 新增客户状态信息
     * 
     * @param customerStatus 客户状态信息
     * @return 结果
     */
    public int insertCustomerStatus(CustomerStatus customerStatus);

    /**
     * 修改客户状态信息
     * 
     * @param customerStatus 客户状态信息
     * @return 结果
     */
    public int updateCustomerStatus(CustomerStatus customerStatus);

    /**
     * 批量删除客户状态信息
     * 
     * @param ids 需要删除的客户状态信息ID
     * @return 结果
     */
    public int deleteCustomerStatusByIds(Long[] ids);

    /**
     * 删除客户状态信息信息
     * 
     * @param id 客户状态信息ID
     * @return 结果
     */
    public int deleteCustomerStatusById(Long id);
}
