package com.lingzhi.crm.customer.service;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerCompanyInfo;
import com.lingzhi.crm.customer.vo.CompanyCustomerAddInVO;
import com.lingzhi.crm.customer.vo.CompanyCustomerDetailOutVO;

/**
 * 客户公司基础信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface ICustomerCompanyInfoService 
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
     * 批量删除客户公司基础信息
     * 
     * @param ids 需要删除的客户公司基础信息ID
     * @return 结果
     */
    public int deleteCustomerCompanyInfoByIds(Long[] ids);

    /**
     * 删除客户公司基础信息信息
     * 
     * @param id 客户公司基础信息ID
     * @return 结果
     */
    public int deleteCustomerCompanyInfoById(Long id);

    /**
     * 新增客户基本信息及联系人相关信息
     * @param inVO
     * @return
     */
    int addCompanyAndContacts(CompanyCustomerAddInVO inVO);

    /**
     * 根据公司id获取公司详情
     * @param companyId
     * @return
     */
    CompanyCustomerDetailOutVO getCompanyDetailById(Long companyId);

    /**
     * 修改客户相关信息
     * @param params
     * @return
     */
    int editCompanyDetail(CompanyCustomerDetailOutVO params);

    /**
     * 分页查询客户详情信息
     * @param companyInfo
     * @return
     */
    List<CompanyCustomerDetailOutVO> selectCustomerCompanyInfoDetailList(CustomerCompanyInfo companyInfo);
}
