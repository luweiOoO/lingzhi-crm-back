package com.lingzhi.crm.customer.mapper;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerSource;

/**
 * 客户来源信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface CustomerSourceMapper 
{
    /**
     * 查询客户来源信息
     * 
     * @param id 客户来源信息ID
     * @return 客户来源信息
     */
    public CustomerSource selectCustomerSourceById(Long id);

    /**
     * 查询客户来源信息列表
     * 
     * @param customerSource 客户来源信息
     * @return 客户来源信息集合
     */
    public List<CustomerSource> selectCustomerSourceList(CustomerSource customerSource);

    /**
     * 新增客户来源信息
     * 
     * @param customerSource 客户来源信息
     * @return 结果
     */
    public int insertCustomerSource(CustomerSource customerSource);

    /**
     * 修改客户来源信息
     * 
     * @param customerSource 客户来源信息
     * @return 结果
     */
    public int updateCustomerSource(CustomerSource customerSource);

    /**
     * 删除客户来源信息
     * 
     * @param id 客户来源信息ID
     * @return 结果
     */
    public int deleteCustomerSourceById(Long id);

    /**
     * 批量删除客户来源信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerSourceByIds(Long[] ids);
}
