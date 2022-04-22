package com.lingzhi.crm.customer.mapper;

import com.lingzhi.crm.customer.domain.CustomerCompanyContacts;
import com.lingzhi.crm.customer.vo.CompanyCustomerContactsDetailOutVO;

import java.util.List;

/**
 * 客户公司联系人信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface CustomerCompanyContactsMapper 
{
    /**
     * 查询客户公司联系人信息
     * 
     * @param id 客户公司联系人信息ID
     * @return 客户公司联系人信息
     */
    public CustomerCompanyContacts selectCustomerCompanyContactsById(Long id);

    /**
     * 查询客户公司联系人信息列表
     * 
     * @param customerCompanyContacts 客户公司联系人信息
     * @return 客户公司联系人信息集合
     */
    public List<CustomerCompanyContacts> selectCustomerCompanyContactsList(CustomerCompanyContacts customerCompanyContacts);

    /**
     * 新增客户公司联系人信息
     * 
     * @param customerCompanyContacts 客户公司联系人信息
     * @return 结果
     */
    public int insertCustomerCompanyContacts(CustomerCompanyContacts customerCompanyContacts);

    /**
     * 修改客户公司联系人信息
     * 
     * @param customerCompanyContacts 客户公司联系人信息
     * @return 结果
     */
    public int updateCustomerCompanyContacts(CustomerCompanyContacts customerCompanyContacts);

    /**
     * 删除客户公司联系人信息
     * 
     * @param id 客户公司联系人信息ID
     * @return 结果
     */
    public int deleteCustomerCompanyContactsById(Long id);

    /**
     * 批量删除客户公司联系人信息
     * 
     * @param idList 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerCompanyContactsByIds(List<Long> idList);

    /**
     * 根据公司id查询公司联系人详情列表
     * @param companyId
     * @return
     */
    List<CompanyCustomerContactsDetailOutVO> getContactsDetailByCompanyId(Long companyId);

    /**
     * 根据客户idList查询出所有联系人IdList
     * @param customerIdList
     * @return
     */
    List<Long> selectContactIdListByCustomerIdList(List<Long> customerIdList);
}
