package com.lingzhi.crm.customer.mapper;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerCompanyInfo;
import com.lingzhi.crm.customer.vo.CompanyCustomerDetailOutVO;

/**
 * 客户公司基础信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface CustomerCompanyInfoMapper 
{
    /**
     * 查询客户公司基础信息
     * 
     * @param id 客户公司基础信息ID
     * @return 客户公司基础信息
     */
    public CustomerCompanyInfo selectCustomerCompanyInfoById(Long id);

    /**
     * 查询客户公司基础信息列表
     * 
     * @param customerCompanyInfo 客户公司基础信息
     * @return 客户公司基础信息集合
     */
    public List<CustomerCompanyInfo> selectCustomerCompanyInfoList(CustomerCompanyInfo customerCompanyInfo);

    /**
     * 新增客户公司基础信息
     * 
     * @param customerCompanyInfo 客户公司基础信息
     * @return 结果
     */
    public int insertCustomerCompanyInfo(CustomerCompanyInfo customerCompanyInfo);

    /**
     * 修改客户公司基础信息
     * 
     * @param customerCompanyInfo 客户公司基础信息
     * @return 结果
     */
    public int updateCustomerCompanyInfo(CustomerCompanyInfo customerCompanyInfo);

    /**
     * 删除客户公司基础信息
     * 
     * @param id 客户公司基础信息ID
     * @return 结果
     */
    public int deleteCustomerCompanyInfoById(Long id);

    /**
     * 批量删除客户公司基础信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerCompanyInfoByIds(Long[] ids);

    /**
     * 根据公司id获取客户公司详细信息
     * @param id
     * @return
     */
    CompanyCustomerDetailOutVO getCompanyDetailById(Long id);

    /**
     * 查询客户公司详情列表(分页查询列表时使用)
     * @param companyInfo
     * @return
     */
    List<CompanyCustomerDetailOutVO> getCompanyDetailList(CustomerCompanyInfo companyInfo);
}
