package com.lingzhi.crm.customer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.customer.domain.CustomerContactsPhone;
import com.lingzhi.crm.customer.domain.CustomerContactsSocialPlatform;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: LuWei
 * @Description 客户公司联系人详情
 * @Date: Created in 16:18 2021/6/3
 * @Modified By:
 */
public class CompanyCustomerContactsDetailOutVO implements Serializable {

    /** 联系人id */
    private Long id;

    /** 所属公司id */
    private Long customerCompanyId;

    /** 昵称 */
    private String contactNickName;

    /** 邮箱 */
    private String contactEmail;

    /** 职级key */
    private String contactRankKey;

    /** 职级value */
    private String contactRankValue;

    /** 职位 */
    private String contactPosition;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contactBirthday;

    /** 性别key */
    private String contactGenderKey;

    /** 性别value */
    private String contactGenderValue;

    /** 备注 */
    private String remarks;

    /** 图片地址 */
    private String imageUrl;

    /** 联系人住址 */
    private String contactAddress;

    /** 联系人邮箱或电话 */
    private String contactEamilPhone;

    /** 创建人昵称 */
    private String createdUserName;

    /** 创建人账号 */
    private String createdUserAccount;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人昵称 */
    private String updatedUserName;

    /** 更新人账号 */
    private String updatedUserAccount;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

    /** 删除标志 0未删除 1已删除 */
    private Integer isDelete;

    /** 所属租户id */
    private Long tenantId;

    /** 联系人联系电话List */
    private List<CustomerContactsPhone> contactsPhoneList;

    /** 联系人社交平台List */
    private List<CustomerContactsSocialPlatform> contactsSocialPlatformList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerCompanyId() {
        return customerCompanyId;
    }

    public void setCustomerCompanyId(Long customerCompanyId) {
        this.customerCompanyId = customerCompanyId;
    }

    public String getContactNickName() {
        return contactNickName;
    }

    public void setContactNickName(String contactNickName) {
        this.contactNickName = contactNickName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactRankKey() {
        return contactRankKey;
    }

    public void setContactRankKey(String contactRankKey) {
        this.contactRankKey = contactRankKey;
    }

    public String getContactRankValue() {
        return contactRankValue;
    }

    public void setContactRankValue(String contactRankValue) {
        this.contactRankValue = contactRankValue;
    }

    public String getContactGenderKey() {
        return contactGenderKey;
    }

    public void setContactGenderKey(String contactGenderKey) {
        this.contactGenderKey = contactGenderKey;
    }

    public String getContactGenderValue() {
        return contactGenderValue;
    }

    public void setContactGenderValue(String contactGenderValue) {
        this.contactGenderValue = contactGenderValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactEamilPhone() {
        return contactEamilPhone;
    }

    public void setContactEamilPhone(String contactEamilPhone) {
        this.contactEamilPhone = contactEamilPhone;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    public String getCreatedUserAccount() {
        return createdUserAccount;
    }

    public void setCreatedUserAccount(String createdUserAccount) {
        this.createdUserAccount = createdUserAccount;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }

    public String getUpdatedUserAccount() {
        return updatedUserAccount;
    }

    public void setUpdatedUserAccount(String updatedUserAccount) {
        this.updatedUserAccount = updatedUserAccount;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public Date getContactBirthday() {
        return contactBirthday;
    }

    public void setContactBirthday(Date contactBirthday) {
        this.contactBirthday = contactBirthday;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public List<CustomerContactsPhone> getContactsPhoneList() {
        return contactsPhoneList;
    }

    public void setContactsPhoneList(List<CustomerContactsPhone> contactsPhoneList) {
        this.contactsPhoneList = contactsPhoneList;
    }

    public List<CustomerContactsSocialPlatform> getContactsSocialPlatformList() {
        return contactsSocialPlatformList;
    }

    public void setContactsSocialPlatformList(List<CustomerContactsSocialPlatform> contactsSocialPlatformList) {
        this.contactsSocialPlatformList = contactsSocialPlatformList;
    }

    @Override
    public String toString() {
        return "CompanyCustomerContactsDetailOutVO{" +
                "id=" + id +
                ", customerCompanyId=" + customerCompanyId +
                ", contactNickName='" + contactNickName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactRankKey='" + contactRankKey + '\'' +
                ", contactRankValue='" + contactRankValue + '\'' +
                ", contactPosition='" + contactPosition + '\'' +
                ", contactBirthday=" + contactBirthday +
                ", contactGenderKey='" + contactGenderKey + '\'' +
                ", contactGenderValue='" + contactGenderValue + '\'' +
                ", remarks='" + remarks + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", contactEamilPhone='" + contactEamilPhone + '\'' +
                ", createdUserName='" + createdUserName + '\'' +
                ", createdUserAccount='" + createdUserAccount + '\'' +
                ", createdTime=" + createdTime +
                ", updatedUserName='" + updatedUserName + '\'' +
                ", updatedUserAccount='" + updatedUserAccount + '\'' +
                ", updatedTime=" + updatedTime +
                ", isDelete=" + isDelete +
                ", tenantId=" + tenantId +
                ", contactsPhoneList=" + contactsPhoneList +
                ", contactsSocialPlatformList=" + contactsSocialPlatformList +
                '}';
    }
}
