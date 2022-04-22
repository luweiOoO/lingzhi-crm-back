package com.lingzhi.crm.customer.service;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerContactsPhone;

/**
 * 客户联系人联系电话信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface ICustomerContactsPhoneService 
{
    /**
     * 查询客户联系人联系电话信息
     * 
     * @param id 客户联系人联系电话信息ID
     * @return 客户联系人联系电话信息
     */
    public CustomerContactsPhone selectCustomerContactsPhoneById(Long id);

    /**
     * 查询客户联系人联系电话信息列表
     * 
     * @param customerContactsPhone 客户联系人联系电话信息
     * @return 客户联系人联系电话信息集合
     */
    public List<CustomerContactsPhone> selectCustomerContactsPhoneList(CustomerContactsPhone customerContactsPhone);

    /**
     * 新增客户联系人联系电话信息
     * 
     * @param customerContactsPhone 客户联系人联系电话信息
     * @return 结果
     */
    public int insertCustomerContactsPhone(CustomerContactsPhone customerContactsPhone);

    /**
     * 修改客户联系人联系电话信息
     * 
     * @param customerContactsPhone 客户联系人联系电话信息
     * @return 结果
     */
    public int updateCustomerContactsPhone(CustomerContactsPhone customerContactsPhone);

    /**
     * 批量删除客户联系人联系电话信息
     * 
     * @param ids 需要删除的客户联系人联系电话信息ID
     * @return 结果
     */
    public int deleteCustomerContactsPhoneByIds(Long[] ids);

    /**
     * 删除客户联系人联系电话信息信息
     * 
     * @param id 客户联系人联系电话信息ID
     * @return 结果
     */
    public int deleteCustomerContactsPhoneById(Long id);

    /**
     * 批量新增客户联系人信息
     * @param contactsPhoneList 客户联系人list
     * @return
     */
    int addCustomerContactsPhoneList(List<CustomerContactsPhone> contactsPhoneList);

    /**
     * 根据联系人id查询联系人联系方式列表
     * @param contactId
     * @return
     */
    List<CustomerContactsPhone> getContactsPhoneListByContactId(Long contactId);

    /**
     * 根据联系人idList批量删除联系人电话信息
     * @param contactIdList
     * @return
     */
    int delContactsPhoneByContactIdList(List<Long> contactIdList);

    /**
     * 根据联系人id和待更新的联系人联系方式列表批量更新联系方式
     * @param contactId
     * @param contactsPhoneList
     * @return
     */
    int editPhoneList(Long contactId,List<CustomerContactsPhone> contactsPhoneList);
}
