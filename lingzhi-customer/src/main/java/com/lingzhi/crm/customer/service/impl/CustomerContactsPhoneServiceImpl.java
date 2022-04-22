package com.lingzhi.crm.customer.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.lingzhi.crm.common.core.domain.model.LoginUser;
import com.lingzhi.crm.common.utils.SecurityUtils;
import com.lingzhi.crm.customer.domain.CustomerContactsSocialPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerContactsPhoneMapper;
import com.lingzhi.crm.customer.domain.CustomerContactsPhone;
import com.lingzhi.crm.customer.service.ICustomerContactsPhoneService;

/**
 * 客户联系人联系电话信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerContactsPhoneServiceImpl implements ICustomerContactsPhoneService 
{
    @Autowired
    private CustomerContactsPhoneMapper customerContactsPhoneMapper;

    /**
     * 查询客户联系人联系电话信息
     * 
     * @param id 客户联系人联系电话信息ID
     * @return 客户联系人联系电话信息
     */
    @Override
    public CustomerContactsPhone selectCustomerContactsPhoneById(Long id)
    {
        return customerContactsPhoneMapper.selectCustomerContactsPhoneById(id);
    }

    /**
     * 查询客户联系人联系电话信息列表
     * 
     * @param customerContactsPhone 客户联系人联系电话信息
     * @return 客户联系人联系电话信息
     */
    @Override
    public List<CustomerContactsPhone> selectCustomerContactsPhoneList(CustomerContactsPhone customerContactsPhone)
    {
        return customerContactsPhoneMapper.selectCustomerContactsPhoneList(customerContactsPhone);
    }

    /**
     * 新增客户联系人联系电话信息
     * 
     * @param customerContactsPhone 客户联系人联系电话信息
     * @return 结果
     */
    @Override
    public int insertCustomerContactsPhone(CustomerContactsPhone customerContactsPhone)
    {
        return customerContactsPhoneMapper.insertCustomerContactsPhone(customerContactsPhone);
    }

    /**
     * 修改客户联系人联系电话信息
     * 
     * @param customerContactsPhone 客户联系人联系电话信息
     * @return 结果
     */
    @Override
    public int updateCustomerContactsPhone(CustomerContactsPhone customerContactsPhone)
    {
        return customerContactsPhoneMapper.updateCustomerContactsPhone(customerContactsPhone);
    }

    /**
     * 批量删除客户联系人联系电话信息
     * 
     * @param ids 需要删除的客户联系人联系电话信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerContactsPhoneByIds(Long[] ids)
    {
        return customerContactsPhoneMapper.deleteCustomerContactsPhoneByIds(ids);
    }

    /**
     * 删除客户联系人联系电话信息信息
     * 
     * @param id 客户联系人联系电话信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerContactsPhoneById(Long id)
    {
        return customerContactsPhoneMapper.deleteCustomerContactsPhoneById(id);
    }

    @Override
    public int addCustomerContactsPhoneList(List<CustomerContactsPhone> contactsPhoneList) {
        return customerContactsPhoneMapper.addContactPhoneList(contactsPhoneList);
    }

    @Override
    public List<CustomerContactsPhone> getContactsPhoneListByContactId(Long contactId) {
        if(contactId == null){
            return new LinkedList<>();
        }
        CustomerContactsPhone query = new CustomerContactsPhone();
        query.setContactId(contactId);
        return customerContactsPhoneMapper.selectCustomerContactsPhoneList(query);
    }

    @Override
    public int delContactsPhoneByContactIdList(List<Long> contactIdList) {
        return customerContactsPhoneMapper.deleteContactPhoneByContactIdList(contactIdList);
    }

    @Override
    public int editPhoneList(Long contactId, List<CustomerContactsPhone> contactsPhoneList) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //待更新联系人电话实体类
        List<CustomerContactsPhone> updatePhoneList = contactsPhoneList.stream()
                .filter(phone -> phone.getId() != null)
                .peek(temp -> {
                    temp.setUpdatedUserName(loginUser.getUser().getNickName());
                    temp.setUpdatedUserAccount(loginUser.getUsername());
                    customerContactsPhoneMapper.updateCustomerContactsPhone(temp);
                })
                .collect(Collectors.toList());

        //待更新联系人电话信息IdList
        List<Long> updatePhoneIdList = updatePhoneList.stream()
                .map(CustomerContactsPhone::getId)
                .collect(Collectors.toList());
        //批量删除该联系人未在更新列表中的电话信息
        customerContactsPhoneMapper.deletePhoneByContactIdAndPhoneIdList(contactId,updatePhoneIdList);
        //待新增联系人电话实体类
        List<CustomerContactsPhone> addPhoneList = contactsPhoneList.stream()
                .filter(phone -> phone.getId() == null)
                .peek(temp -> {
                    temp.setContactId(contactId);
                    temp.setCreatedUserAccount(loginUser.getUsername());
                    temp.setCreatedUserName(loginUser.getUser().getNickName());
                    temp.setCreatedTime(new Date());
                })
                .collect(Collectors.toList());
        //批量新增联系人电话信息
        if(addPhoneList.size() > 0){
            addCustomerContactsPhoneList(addPhoneList);
        }
        return 1;
    }
}
