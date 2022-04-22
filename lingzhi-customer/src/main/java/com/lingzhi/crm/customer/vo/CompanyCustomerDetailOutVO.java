package com.lingzhi.crm.customer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: LuWei
 * @Description
 * @Date: Created in 16:06 2021/6/3
 * @Modified By:
 */
public class CompanyCustomerDetailOutVO implements Serializable {

    /** 客户id */
    public Long id;

    /** 客户编码 */
    private String customerCode;

    /** 公司名称 */
    private String companyName;

    /** 公司简称 */
    private String companyNameShorter;

    /** 主营产品类别idList */
    private List<Long> mainProductCategoryIdList;

    /** 客户来源id */
    private Long customerSourceId;

    /** 客户来源 */
    private String customerSourceName;

    /** 客户年采购额key */
    private String customerProcurementKey;

    /** 客户年采购额value */
    private String customerProcurementValue;

    /** 客户采购意向key */
    private String purchaseIntentionKey;

    /** 客户采购意向value */
    private String purchaseIntentionValue;

    /** 所属国家区域id */
    private Long regionId;

    /** 所属国家区域name */
    private String regionName;

    /** 所属时区id */
    private Long timeZoneId;

    /** 所属时区name */
    private String timeZoneName;

    /** 公司规模key */
    private String companySizeKey;

    /** 公司规模value */
    private String companySizeValue;

    /** 客户类别id */
    private Long customerCategoryId;

    /** 客户类别name */
    private String customerCategoryName;

    /** 客户所属分组id */
    private Long customerGourpId;

    /** 客户所属分组Name */
    private String customerGourpName;

    /** 客户所属公海分组id */
    private Long customerPublicGourpId;

    /** 客户所属公海分组name */
    private String customerPublicGourpName;

    /** 客户状态id */
    private Long customerStatusId;

    /** 客户状态name */
    private String customerStatusName;

    /** 客户标签id */
    private Long customerLabelId;

    /** 客户标签名称 */
    private String customerLabelName;

    /** 客户星级key */
    private String customerStarLevelKey;

    /** 客户星级value */
    private String customerStarLevelValue;

    /** 下次跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nextFollowTime;

    /** 备注 */
    private String companyRemarks;

    /** 座机前缀 */
    private String regionPhonePrefix;

    /** 座机 */
    private String phone;

    /** 图片 */
    private String companyImageUrl;

    /** 主页地址 */
    private String companyHomepageUrl;

    /** 传真 */
    private String customerFax;

    /** 客户联系人列表 */
    private List<CompanyCustomerContactsDetailOutVO> contactsList;

    /** 所属租户id */
    private Long tenantId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNameShorter() {
        return companyNameShorter;
    }

    public void setCompanyNameShorter(String companyNameShorter) {
        this.companyNameShorter = companyNameShorter;
    }

    public Long getCustomerSourceId() {
        return customerSourceId;
    }

    public void setCustomerSourceId(Long customerSourceId) {
        this.customerSourceId = customerSourceId;
    }

    public String getCustomerSourceName() {
        return customerSourceName;
    }

    public void setCustomerSourceName(String customerSourceName) {
        this.customerSourceName = customerSourceName;
    }

    public String getCustomerProcurementKey() {
        return customerProcurementKey;
    }

    public void setCustomerProcurementKey(String customerProcurementKey) {
        this.customerProcurementKey = customerProcurementKey;
    }

    public String getCustomerProcurementValue() {
        return customerProcurementValue;
    }

    public void setCustomerProcurementValue(String customerProcurementValue) {
        this.customerProcurementValue = customerProcurementValue;
    }

    public String getPurchaseIntentionKey() {
        return purchaseIntentionKey;
    }

    public void setPurchaseIntentionKey(String purchaseIntentionKey) {
        this.purchaseIntentionKey = purchaseIntentionKey;
    }

    public String getPurchaseIntentionValue() {
        return purchaseIntentionValue;
    }

    public void setPurchaseIntentionValue(String purchaseIntentionValue) {
        this.purchaseIntentionValue = purchaseIntentionValue;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Long getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(Long timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }

    public String getCompanySizeKey() {
        return companySizeKey;
    }

    public void setCompanySizeKey(String companySizeKey) {
        this.companySizeKey = companySizeKey;
    }

    public String getCompanySizeValue() {
        return companySizeValue;
    }

    public void setCompanySizeValue(String companySizeValue) {
        this.companySizeValue = companySizeValue;
    }

    public Long getCustomerCategoryId() {
        return customerCategoryId;
    }

    public void setCustomerCategoryId(Long customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    public String getCustomerCategoryName() {
        return customerCategoryName;
    }

    public void setCustomerCategoryName(String customerCategoryName) {
        this.customerCategoryName = customerCategoryName;
    }

    public Long getCustomerGourpId() {
        return customerGourpId;
    }

    public void setCustomerGourpId(Long customerGourpId) {
        this.customerGourpId = customerGourpId;
    }

    public String getCustomerGourpName() {
        return customerGourpName;
    }

    public void setCustomerGourpName(String customerGourpName) {
        this.customerGourpName = customerGourpName;
    }

    public Long getCustomerPublicGourpId() {
        return customerPublicGourpId;
    }

    public void setCustomerPublicGourpId(Long customerPublicGourpId) {
        this.customerPublicGourpId = customerPublicGourpId;
    }

    public String getCustomerPublicGourpName() {
        return customerPublicGourpName;
    }

    public void setCustomerPublicGourpName(String customerPublicGourpName) {
        this.customerPublicGourpName = customerPublicGourpName;
    }

    public Long getCustomerStatusId() {
        return customerStatusId;
    }

    public void setCustomerStatusId(Long customerStatusId) {
        this.customerStatusId = customerStatusId;
    }

    public String getCustomerStatusName() {
        return customerStatusName;
    }

    public void setCustomerStatusName(String customerStatusName) {
        this.customerStatusName = customerStatusName;
    }

    public Long getCustomerLabelId() {
        return customerLabelId;
    }

    public void setCustomerLabelId(Long customerLabelId) {
        this.customerLabelId = customerLabelId;
    }

    public String getCustomerLabelName() {
        return customerLabelName;
    }

    public void setCustomerLabelName(String customerLabelName) {
        this.customerLabelName = customerLabelName;
    }

    public String getCustomerStarLevelKey() {
        return customerStarLevelKey;
    }

    public void setCustomerStarLevelKey(String customerStarLevelKey) {
        this.customerStarLevelKey = customerStarLevelKey;
    }

    public String getCustomerStarLevelValue() {
        return customerStarLevelValue;
    }

    public void setCustomerStarLevelValue(String customerStarLevelValue) {
        this.customerStarLevelValue = customerStarLevelValue;
    }

    public Date getNextFollowTime() {
        return nextFollowTime;
    }

    public void setNextFollowTime(Date nextFollowTime) {
        this.nextFollowTime = nextFollowTime;
    }

    public String getCompanyRemarks() {
        return companyRemarks;
    }

    public void setCompanyRemarks(String companyRemarks) {
        this.companyRemarks = companyRemarks;
    }

    public String getRegionPhonePrefix() {
        return regionPhonePrefix;
    }

    public void setRegionPhonePrefix(String regionPhonePrefix) {
        this.regionPhonePrefix = regionPhonePrefix;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyImageUrl() {
        return companyImageUrl;
    }

    public void setCompanyImageUrl(String companyImageUrl) {
        this.companyImageUrl = companyImageUrl;
    }

    public String getCompanyHomepageUrl() {
        return companyHomepageUrl;
    }

    public void setCompanyHomepageUrl(String companyHomepageUrl) {
        this.companyHomepageUrl = companyHomepageUrl;
    }

    public String getCustomerFax() {
        return customerFax;
    }

    public void setCustomerFax(String customerFax) {
        this.customerFax = customerFax;
    }

    public List<CompanyCustomerContactsDetailOutVO> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<CompanyCustomerContactsDetailOutVO> contactsList) {
        this.contactsList = contactsList;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
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

    public List<Long> getMainProductCategoryIdList() {
        return mainProductCategoryIdList;
    }

    public void setMainProductCategoryIdList(List<Long> mainProductCategoryIdList) {
        this.mainProductCategoryIdList = mainProductCategoryIdList;
    }

    @Override
    public String toString() {
        return "CompanyCustomerDetailOutVO{" +
                "id=" + id +
                ", customerCode='" + customerCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyNameShorter='" + companyNameShorter + '\'' +
                ", mainProductCategoryIdList=" + mainProductCategoryIdList +
                ", customerSourceId=" + customerSourceId +
                ", customerSourceName='" + customerSourceName + '\'' +
                ", customerProcurementKey='" + customerProcurementKey + '\'' +
                ", customerProcurementValue='" + customerProcurementValue + '\'' +
                ", purchaseIntentionKey='" + purchaseIntentionKey + '\'' +
                ", purchaseIntentionValue='" + purchaseIntentionValue + '\'' +
                ", regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                ", timeZoneId=" + timeZoneId +
                ", timeZoneName='" + timeZoneName + '\'' +
                ", companySizeKey='" + companySizeKey + '\'' +
                ", companySizeValue='" + companySizeValue + '\'' +
                ", customerCategoryId=" + customerCategoryId +
                ", customerCategoryName='" + customerCategoryName + '\'' +
                ", customerGourpId=" + customerGourpId +
                ", customerGourpName='" + customerGourpName + '\'' +
                ", customerPublicGourpId=" + customerPublicGourpId +
                ", customerPublicGourpName='" + customerPublicGourpName + '\'' +
                ", customerStatusId=" + customerStatusId +
                ", customerStatusName='" + customerStatusName + '\'' +
                ", customerLabelId=" + customerLabelId +
                ", customerLabelName='" + customerLabelName + '\'' +
                ", customerStarLevelKey='" + customerStarLevelKey + '\'' +
                ", customerStarLevelValue='" + customerStarLevelValue + '\'' +
                ", nextFollowTime=" + nextFollowTime +
                ", companyRemarks='" + companyRemarks + '\'' +
                ", regionPhonePrefix='" + regionPhonePrefix + '\'' +
                ", phone='" + phone + '\'' +
                ", companyImageUrl='" + companyImageUrl + '\'' +
                ", companyHomepageUrl='" + companyHomepageUrl + '\'' +
                ", customerFax='" + customerFax + '\'' +
                ", contactsList=" + contactsList +
                ", tenantId=" + tenantId +
                ", createdUserName='" + createdUserName + '\'' +
                ", createdUserAccount='" + createdUserAccount + '\'' +
                ", createdTime=" + createdTime +
                ", updatedUserName='" + updatedUserName + '\'' +
                ", updatedUserAccount='" + updatedUserAccount + '\'' +
                ", updatedTime=" + updatedTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
