package com.lingzhi.crm.customer.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.lingzhi.crm.common.core.domain.model.LoginUser;
import com.lingzhi.crm.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.customer.mapper.CustomerContactsSocialPlatformMapper;
import com.lingzhi.crm.customer.domain.CustomerContactsSocialPlatform;
import com.lingzhi.crm.customer.service.ICustomerContactsSocialPlatformService;

/**
 * 客户联系人社交账号信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CustomerContactsSocialPlatformServiceImpl implements ICustomerContactsSocialPlatformService 
{
    @Autowired
    private CustomerContactsSocialPlatformMapper customerContactsSocialPlatformMapper;

    /**
     * 查询客户联系人社交账号信息
     * 
     * @param id 客户联系人社交账号信息ID
     * @return 客户联系人社交账号信息
     */
    @Override
    public CustomerContactsSocialPlatform selectCustomerContactsSocialPlatformById(Long id)
    {
        return customerContactsSocialPlatformMapper.selectCustomerContactsSocialPlatformById(id);
    }

    /**
     * 查询客户联系人社交账号信息列表
     * 
     * @param customerContactsSocialPlatform 客户联系人社交账号信息
     * @return 客户联系人社交账号信息
     */
    @Override
    public List<CustomerContactsSocialPlatform> selectCustomerContactsSocialPlatformList(CustomerContactsSocialPlatform customerContactsSocialPlatform)
    {
        return customerContactsSocialPlatformMapper.selectCustomerContactsSocialPlatformList(customerContactsSocialPlatform);
    }

    /**
     * 新增客户联系人社交账号信息
     * 
     * @param customerContactsSocialPlatform 客户联系人社交账号信息
     * @return 结果
     */
    @Override
    public int insertCustomerContactsSocialPlatform(CustomerContactsSocialPlatform customerContactsSocialPlatform)
    {
        return customerContactsSocialPlatformMapper.insertCustomerContactsSocialPlatform(customerContactsSocialPlatform);
    }

    /**
     * 修改客户联系人社交账号信息
     * 
     * @param customerContactsSocialPlatform 客户联系人社交账号信息
     * @return 结果
     */
    @Override
    public int updateCustomerContactsSocialPlatform(CustomerContactsSocialPlatform customerContactsSocialPlatform)
    {
        return customerContactsSocialPlatformMapper.updateCustomerContactsSocialPlatform(customerContactsSocialPlatform);
    }

    /**
     * 批量删除客户联系人社交账号信息
     * 
     * @param ids 需要删除的客户联系人社交账号信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerContactsSocialPlatformByIds(Long[] ids)
    {
        return customerContactsSocialPlatformMapper.deleteCustomerContactsSocialPlatformByIds(ids);
    }

    /**
     * 删除客户联系人社交账号信息信息
     * 
     * @param id 客户联系人社交账号信息ID
     * @return 结果
     */
    @Override
    public int deleteCustomerContactsSocialPlatformById(Long id)
    {
        return customerContactsSocialPlatformMapper.deleteCustomerContactsSocialPlatformById(id);
    }

    @Override
    public int addContactsSocialPlatformList(List<CustomerContactsSocialPlatform> contactsSocialPlatformList) {
        return customerContactsSocialPlatformMapper.addContactsSocialPlatformList(contactsSocialPlatformList);
    }

    @Override
    public List<CustomerContactsSocialPlatform> getPlatformListByContactId(Long contactId) {
        if(contactId == null){
            return new LinkedList<>();
        }
        CustomerContactsSocialPlatform query = new CustomerContactsSocialPlatform();
        query.setContactId(contactId);
        return customerContactsSocialPlatformMapper.selectCustomerContactsSocialPlatformList(query);
    }

    @Override
    public int delPlatformByContactIdList(List<Long> contactIdList) {
        return customerContactsSocialPlatformMapper.deletePlatformByContactIdList(contactIdList);
    }

    @Override
    public int editPlatformList(Long contactId, List<CustomerContactsSocialPlatform> socialPlatformList) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //待更新联系人社交平台实体类
        List<CustomerContactsSocialPlatform> updatePlatformList = socialPlatformList.stream()
                .filter(platform -> platform.getId() != null)
                .peek(temp -> {
                    temp.setUpdatedUserName(loginUser.getUser().getNickName());
                    temp.setUpdatedUserAccount(loginUser.getUsername());
                    customerContactsSocialPlatformMapper.updateCustomerContactsSocialPlatform(temp);
                })
                .collect(Collectors.toList());

        //待更新联系人社交平台信息IdList
        List<Long> updatePlatformIdList = updatePlatformList.stream()
                .map(CustomerContactsSocialPlatform::getId)
                .collect(Collectors.toList());
        //批量删除该联系人未在更新列表中的账号信息
        customerContactsSocialPlatformMapper.deletePlatFormByContactIdAndPlatFormIdList(contactId,updatePlatformIdList);
        //待新增联系人社交平台实体类
        List<CustomerContactsSocialPlatform> addPlatformList = socialPlatformList.stream()
                .filter(platform -> platform.getId() == null)
                .peek(temp -> {
                    temp.setContactId(contactId);
                    temp.setCreatedUserAccount(loginUser.getUsername());
                    temp.setCreatedUserName(loginUser.getUser().getNickName());
                    temp.setCreatedTime(new Date());
                })
                .collect(Collectors.toList());
        //批量新增联系人社交平台账号信息
        if(addPlatformList.size() > 0){
            addContactsSocialPlatformList(addPlatformList);
        }
        return 1;
    }
}
