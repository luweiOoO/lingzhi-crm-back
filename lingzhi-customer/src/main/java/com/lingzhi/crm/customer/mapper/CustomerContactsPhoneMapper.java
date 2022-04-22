package com.lingzhi.crm.customer.mapper;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerContactsPhone;
import org.apache.ibatis.annotations.Param;

/**
 * 客户联系人联系电话信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface CustomerContactsPhoneMapper 
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
     * 删除客户联系人联系电话信息
     * 
     * @param id 客户联系人联系电话信息ID
     * @return 结果
     */
    public int deleteCustomerContactsPhoneById(Long id);

    /**
     * 批量删除客户联系人联系电话信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerContactsPhoneByIds(Long[] ids);

    /**
     * 批量新增客户联系人电话信息
     * @param contactsPhoneList
     * @return
     */
    int addContactPhoneList(List<CustomerContactsPhone> contactsPhoneList);

    /**
     * 根据联系人idList批量逻辑删除联系人手机号信息
     * @param contactIdList
     * @return
     */
    int deleteContactPhoneByContactIdList(List<Long> contactIdList);

    /**
     * 批量删除指定联系人不在该电话列表中的电话信息
     * @param contactId 联系人id
     * @param phoneIdList 电话id
     * @return
     */
    int deletePhoneByContactIdAndPhoneIdList(@Param("contactId") Long contactId,@Param("phoneIdList") List<Long> phoneIdList);
}
