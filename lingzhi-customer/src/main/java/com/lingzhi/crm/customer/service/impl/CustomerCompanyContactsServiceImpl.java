package com.lingzhi.crm.customer.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.lingzhi.crm.common.core.domain.model.LoginUser;
import com.lingzhi.crm.common.domain.ProductSalesManOrderImportEntity;
import com.lingzhi.crm.common.utils.SecurityUtils;
import com.lingzhi.crm.customer.domain.CustomerCompanyInfo;
import com.lingzhi.crm.customer.domain.CustomerContactsPhone;
import com.lingzhi.crm.customer.domain.CustomerContactsSocialPlatform;
import com.lingzhi.crm.customer.service.ICustomerCompanyContactsService;
import com.lingzhi.crm.customer.service.ICustomerCompanyInfoService;
import com.lingzhi.crm.customer.service.ICustomerContactsPhoneService;
import com.lingzhi.crm.customer.service.ICustomerContactsSocialPlatformService;
import com.lingzhi.crm.customer.vo.CompanyCustomerAddInVO;
import com.lingzhi.crm.customer.vo.CompanyCustomerContactsAddInVO;
import com.lingzhi.crm.customer.vo.CompanyCustomerContactsDetailOutVO;
import com.lingzhi.crm.customer.vo.CompanyCustomerContactsDto;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerCompanyContactsMapper;
import com.lingzhi.crm.customer.domain.CustomerCompanyContacts;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户公司联系人信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerCompanyContactsServiceImpl implements ICustomerCompanyContactsService
{
    @Autowired
    private CustomerCompanyContactsMapper customerCompanyContactsMapper;
    @Autowired
    private ICustomerContactsPhoneService contactsPhoneService;
    @Autowired
    private ICustomerContactsSocialPlatformService contactsSocialPlatformService;
    @Autowired
    private ICustomerCompanyInfoService customerCompanyInfoService;

    /**
     * 查询客户公司联系人信息
     * 
     * @param id 客户公司联系人信息ID
     * @return 客户公司联系人信息
     */
    @Override
    public CustomerCompanyContacts selectCustomerCompanyContactsById(Long id)
    {
        return customerCompanyContactsMapper.selectCustomerCompanyContactsById(id);
    }

    /**
     * 查询客户公司联系人信息列表
     * 
     * @param customerCompanyContacts 客户公司联系人信息
     * @return 客户公司联系人信息
     */
    @Override
    public List<CustomerCompanyContacts> selectCustomerCompanyContactsList(CustomerCompanyContacts customerCompanyContacts)
    {
        return customerCompanyContactsMapper.selectCustomerCompanyContactsList(customerCompanyContacts);
    }

    /**
     * 新增客户公司联系人信息
     * 
     * @param customerCompanyContacts 客户公司联系人信息
     * @return 结果
     */
    @Override
    public int insertCustomerCompanyContacts(CustomerCompanyContacts customerCompanyContacts)
    {
        return customerCompanyContactsMapper.insertCustomerCompanyContacts(customerCompanyContacts);
    }

    /**
     * 修改客户公司联系人信息
     * 
     * @param customerCompanyContacts 客户公司联系人信息
     * @return 结果
     */
    @Override
    public int updateCustomerCompanyContacts(CustomerCompanyContacts customerCompanyContacts)
    {
        return customerCompanyContactsMapper.updateCustomerCompanyContacts(customerCompanyContacts);
    }

    /**
     * 批量删除客户公司联系人信息
     * 
     * @param idList 需要删除的客户公司联系人信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerCompanyContactsByIds(List<Long> idList)
    {
        return customerCompanyContactsMapper.deleteCustomerCompanyContactsByIds(idList);
    }

    /**
     * 删除客户公司联系人信息信息
     * 
     * @param id 客户公司联系人信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerCompanyContactsById(Long id)
    {
        return customerCompanyContactsMapper.deleteCustomerCompanyContactsById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCompanyContactsAndPhonePlatform(Long companyId, List<CompanyCustomerContactsAddInVO> contactsAddInVoList) {
        if(companyId == null || contactsAddInVoList == null || contactsAddInVoList.size() < 1){
            return 0;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        contactsAddInVoList.forEach(contactVo -> {
            CustomerCompanyContacts tempContact = new CustomerCompanyContacts();
            BeanUtils.copyProperties(contactVo,tempContact);
            tempContact.setCustomerCompanyId(companyId);
            tempContact.setCreatedTime(new Date());
            tempContact.setCreatedUserAccount(loginUser.getUser().getUserName());
            tempContact.setCreatedUserName(loginUser.getUser().getNickName());
            //TODO 设置租户id
            //新增联系人
            customerCompanyContactsMapper.insertCustomerCompanyContacts(tempContact);

            List<CustomerContactsPhone> contactsPhoneList = contactVo.getContactsPhoneList();
            if(contactsPhoneList != null && contactsPhoneList.size() > 0){
                //联系人电话信息列表数据赋值联系人id
                contactsPhoneList.forEach(contactPhone -> contactPhone.setContactId(tempContact.getId()));
                //批量新增联系人电话
                contactsPhoneService.addCustomerContactsPhoneList(contactsPhoneList);
            }

            List<CustomerContactsSocialPlatform> contactsSocialPlatformList = contactVo.getContactsSocialPlatformList();
            if(contactsSocialPlatformList != null && contactsSocialPlatformList.size() > 0){
                //联系人社交平台列表数据辅助联系人id
                contactsSocialPlatformList.forEach(platform -> platform.setContactId(tempContact.getId()));
                //批量新增联系人社交平台
                contactsSocialPlatformService.addContactsSocialPlatformList(contactsSocialPlatformList);
            }
        });
        return 1;
    }

    @Override
    public List<CompanyCustomerContactsDetailOutVO> getContactsDetailListByCompanyId(Long companyId) {
        if(companyId == null){
            return new LinkedList<>();
        }
        //1、查询联系人基本信息
        List<CompanyCustomerContactsDetailOutVO> detailOutVOList = customerCompanyContactsMapper.getContactsDetailByCompanyId(companyId);
        //2、TODO 填充联系人基本信息中得字典value值

        if(detailOutVOList != null && detailOutVOList.size() > 0){
            detailOutVOList.forEach(detailOutTemp -> {
                //查询联系人电话信息
                List<CustomerContactsPhone> contactsPhoneList = contactsPhoneService.getContactsPhoneListByContactId(detailOutTemp.getId());
                detailOutTemp.setContactsPhoneList(contactsPhoneList);
                //查询联系人社交平台信息
                List<CustomerContactsSocialPlatform> contactsSocialPlatformList = contactsSocialPlatformService.getPlatformListByContactId(detailOutTemp.getId());
                detailOutTemp.setContactsSocialPlatformList(contactsSocialPlatformList);
            });
        }

        return detailOutVOList;
    }

    @Override
    public int editCustomerContactsDetail(Long customerId, List<CompanyCustomerContactsDetailOutVO> contactList) {
        if(customerId == null){
            return 0;
        }
        //总体原则--不带联系人id的为要新增的 带联系人id的为要更新的 还需判断已删除的
        //需更新的客户联系人idList
        List<Long> updateContactIdList = new LinkedList<>();
        //需更新的客户联系人信息
        List<CompanyCustomerContactsDetailOutVO> updateContactList = new LinkedList<>();
        //需新增的客户联系人信息
        List<CompanyCustomerContactsDetailOutVO> addContactList = new LinkedList<>();
        if(contactList != null && contactList.size() > 0){
            //筛选出需更新的联系人信息
            updateContactList = contactList.stream()
                    .filter(contact -> contact.getId() != null)
                    .collect(Collectors.toList());
            //筛选出需更新的联系人id
            updateContactIdList = updateContactList.stream().map(CompanyCustomerContactsDetailOutVO::getId).collect(Collectors.toList());
            //筛选出需新增的联系人信息
            addContactList = contactList.stream()
                    .filter(contact -> contact.getId() == null)
                    .collect(Collectors.toList());
        }
        //未更新前客户已维护的联系人idList
        List<Long> customerIdList = new LinkedList<>();
        customerIdList.add(customerId);
        List<Long> oldContactIdList = getContactIdListByCustomerId(customerIdList);

        //筛选出旧版联系人列表中不包含更新联系人列表的数据---需删除
        List<Long> finalUpdateContactIdList = updateContactIdList;
        List<Long> delContactIdList = oldContactIdList.stream()
                .filter(contactId -> !finalUpdateContactIdList.contains(contactId))
                .collect(Collectors.toList());

        //删除更新时需删除的联系人信息
        if(delContactIdList.size() > 0){
            delContactsByIdList(delContactIdList);
        }

        //新增联系人列表转换对象
        List<CompanyCustomerContactsAddInVO> addContactVoList = new LinkedList<>();
        addContactList.forEach(tempContact -> {
            CompanyCustomerContactsAddInVO tempVo = new CompanyCustomerContactsAddInVO();
            BeanUtils.copyProperties(tempContact,tempVo);
            addContactVoList.add(tempVo);
        });
        //批量新增联系人信息
        addCompanyContactsAndPhonePlatform(customerId,addContactVoList);

        //批量更新联系人主表信息
        updateContactList.forEach(tempContact -> {
            CustomerCompanyContacts contact = new CustomerCompanyContacts();
            BeanUtils.copyProperties(tempContact,contact);
            //更新联系人主表数据
            customerCompanyContactsMapper.updateCustomerCompanyContacts(contact);
            //更新联系人社交平台账号信息
            contactsSocialPlatformService.editPlatformList(contact.getId(), tempContact.getContactsSocialPlatformList());
            //更新联系人联系方式信息
            contactsPhoneService.editPhoneList(contact.getId(), tempContact.getContactsPhoneList());
        });
        return 1;

    }

    @Override
    public int delContactsByIdList(List<Long> contactIdList) {
        //1、批量逻辑删除联系人主表信息
        customerCompanyContactsMapper.deleteCustomerCompanyContactsByIds(contactIdList);
        //2、批量逻辑删除联系人电话信息
        contactsPhoneService.delContactsPhoneByContactIdList(contactIdList);
        //3、批量逻辑删除联系人社交平台信息
        contactsSocialPlatformService.delPlatformByContactIdList(contactIdList);

        return 1;
    }

    @Override
    public List<Long> getContactIdListByCustomerId(List<Long> customerIdList) {
        List<Long> contactIdList = new LinkedList<>();
        if(customerIdList != null && customerIdList.size() > 0){
            contactIdList = customerCompanyContactsMapper.selectContactIdListByCustomerIdList(customerIdList);
        }
        return contactIdList;
    }

    @Override
    public void insertOrUpdateCustomerContact(ProductSalesManOrderImportEntity entity) {
        //1、根据公司名称查询是否已录入该公司
        CustomerCompanyInfo companyInfo = customerCompanyInfoService.selectCompanyInfoByName(entity.getCustomerCompanyName());
        //若公司为空 则需新增公司及联系人信息
        if(companyInfo == null){
            CompanyCustomerAddInVO customerAddInVO = new CompanyCustomerAddInVO();
            customerAddInVO.setCompanyName(entity.getCustomerCompanyName());
            CompanyCustomerContactsAddInVO contactsAddInVO = new CompanyCustomerContactsAddInVO();
            contactsAddInVO.setContactAddress(entity.getCustomerAddress());
            contactsAddInVO.setContactNickName(entity.getCustomerNickName());
            contactsAddInVO.setContactName(entity.getCustomerName());
            CustomerContactsPhone contactsPhone = new CustomerContactsPhone();
            contactsPhone.setPhoneNumber(entity.getCustomerPhone());
            contactsAddInVO.setContactsPhoneList(Collections.singletonList(contactsPhone));
            List<CompanyCustomerContactsAddInVO> contactsList = Collections.singletonList(contactsAddInVO);
            customerAddInVO.setContactsList(contactsList);
            customerCompanyInfoService.addCompanyAndContacts(customerAddInVO);
        }else {
            //根据客户名称和联系人姓名查询该公司所属联系人是否为空
            CustomerCompanyContacts companyContacts = this.getContactDetailByCompanyNameAndContactName(entity.getCustomerCompanyName(),entity.getCustomerName());
            if(companyContacts == null){
                //若联系人信息为空 则新增联系人
            }else {
                //若联系人信息不为空，则更新联系人信息
            }
        }

    }

    /**
     * 根据公司名称和联系人名称查询客户详情
     * @param companyName 公司名称
     * @param contactName 联系人名称
     * @return
     */
    private CompanyCustomerContactsDto getContactDetailByCompanyNameAndContactName(String companyName,String contactName){
        CompanyCustomerContactsDto contactsAndPhoneDto = customerCompanyContactsMapper.getContactsDetailByCompanyNameAndContactName(companyName, contactName);
        return contactsAndPhoneDto;
    }

}
