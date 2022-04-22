package com.lingzhi.crm.customer.service;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerContactsSocialPlatform;

/**
 * 客户联系人社交账号信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface ICustomerContactsSocialPlatformService 
{
    /**
     * 查询客户联系人社交账号信息
     * 
     * @param id 客户联系人社交账号信息ID
     * @return 客户联系人社交账号信息
     */
    public CustomerContactsSocialPlatform selectCustomerContactsSocialPlatformById(Long id);

    /**
     * 查询客户联系人社交账号信息列表
     * 
     * @param customerContactsSocialPlatform 客户联系人社交账号信息
     * @return 客户联系人社交账号信息集合
     */
    public List<CustomerContactsSocialPlatform> selectCustomerContactsSocialPlatformList(CustomerContactsSocialPlatform customerContactsSocialPlatform);

    /**
     * 新增客户联系人社交账号信息
     * 
     * @param customerContactsSocialPlatform 客户联系人社交账号信息
     * @return 结果
     */
    public int insertCustomerContactsSocialPlatform(CustomerContactsSocialPlatform customerContactsSocialPlatform);

    /**
     * 修改客户联系人社交账号信息
     * 
     * @param customerContactsSocialPlatform 客户联系人社交账号信息
     * @return 结果
     */
    public int updateCustomerContactsSocialPlatform(CustomerContactsSocialPlatform customerContactsSocialPlatform);

    /**
     * 批量删除客户联系人社交账号信息
     * 
     * @param ids 需要删除的客户联系人社交账号信息ID
     * @return 结果
     */
    public int deleteCustomerContactsSocialPlatformByIds(Long[] ids);

    /**
     * 删除客户联系人社交账号信息信息
     * 
     * @param id 客户联系人社交账号信息ID
     * @return 结果
     */
    public int deleteCustomerContactsSocialPlatformById(Long id);

    /**
     * 批量新增联系人社交平台账号
     * @param contactsSocialPlatformList 社交平台账号列表
     * @return
     */
    int addContactsSocialPlatformList(List<CustomerContactsSocialPlatform> contactsSocialPlatformList);

    /**
     * 根据联系人id获取联系人社交平台列表
     * @param contactId
     * @return
     */
    List<CustomerContactsSocialPlatform> getPlatformListByContactId(Long contactId);

    /**
     * 根据联系人idList批量逻辑删除联系人社交平台账号
     * @param contactIdList
     * @return
     */
    int delPlatformByContactIdList(List<Long> contactIdList);

    /**
     * 根据联系人id和待更新联系人社交账号批量更新联系人社交平台信息
     * @param contactId
     * @param socialPlatformList
     * @return
     */
    int editPlatformList(Long contactId,List<CustomerContactsSocialPlatform> socialPlatformList);
}