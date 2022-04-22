package com.lingzhi.crm.customer.mapper;

import java.util.List;
import com.lingzhi.crm.customer.domain.CustomerContactsSocialPlatform;
import org.apache.ibatis.annotations.Param;

/**
 * 客户联系人社交账号信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface CustomerContactsSocialPlatformMapper 
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
     * 删除客户联系人社交账号信息
     * 
     * @param id 客户联系人社交账号信息ID
     * @return 结果
     */
    public int deleteCustomerContactsSocialPlatformById(Long id);

    /**
     * 批量删除客户联系人社交账号信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerContactsSocialPlatformByIds(Long[] ids);

    /**
     * 批量新增联系人社交平台信息
     * @param contactsSocialPlatformList
     * @return
     */
    int addContactsSocialPlatformList(List<CustomerContactsSocialPlatform> contactsSocialPlatformList);

    /**
     * 根据联系人idList批量逻辑删除联系人社交平台信息
     * @param contactIdList
     * @return
     */
    int deletePlatformByContactIdList(List<Long> contactIdList);

    /**
     * 删除指定联系人未在联系人社交帐号列表Id中的账号信息
     * @param contactId 联系人id
     * @param platformIdList 联系人社交账号idList
     * @return
     */
    int deletePlatFormByContactIdAndPlatFormIdList(@Param("contactId") Long contactId, @Param("platformIdList") List<Long> platformIdList);
}
