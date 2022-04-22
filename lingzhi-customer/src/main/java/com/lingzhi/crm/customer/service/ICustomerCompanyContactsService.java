package com.lingzhi.crm.customer.service;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerCompanyContacts;
import com.lingzhi.crm.customer.vo.CompanyCustomerContactsAddInVO;
import com.lingzhi.crm.customer.vo.CompanyCustomerContactsDetailOutVO;

/**
 * 客户公司联系人信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface ICustomerCompanyContactsService 
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
     * 批量删除客户公司联系人信息
     * 
     * @param idList 需要删除的客户公司联系人信息ID
     * @return 结果
     */
    public int deleteCustomerCompanyContactsByIds(List<Long> idList);

    /**
     * 删除客户公司联系人信息信息
     * 
     * @param id 客户公司联系人信息ID
     * @return 结果
     */
    public int deleteCustomerCompanyContactsById(Long id);

    /**
     * 新增联系人基本信息和联系方式以及社交平台
     * @param companyId 联系人所属公司id
     * @param contactsAddInVoList 联系人信息列表
     * @return
     */
    int addCompanyContactsAndPhonePlatform(Long companyId, List<CompanyCustomerContactsAddInVO> contactsAddInVoList);

    /**
     * 根据公司id查询公司联系人列表
     * @param companyId
     * @return
     */
    List<CompanyCustomerContactsDetailOutVO> getContactsDetailListByCompanyId(Long companyId);

    /**
     * 批量更新客户联系人相关信息
     * @param customerId
     * @param contactList
     * @return
     */
    int editCustomerContactsDetail(Long customerId,List<CompanyCustomerContactsDetailOutVO> contactList);

    /**
     * 根据联系人id列表批量删除联系人相关信息
     * @param contactIdList
     * @return
     */
    int delContactsByIdList(List<Long> contactIdList);

    /**
     * 根据客户idList查询出该客户所属所有联系人idList
     * @param customerIdList
     * @return
     */
    List<Long> getContactIdListByCustomerId(List<Long> customerIdList);

}
