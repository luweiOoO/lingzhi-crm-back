package com.lingzhi.crm.customer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: LuWei
 * @Description 客户基本信息新增接口请求参数
 * @Date: Created in 10:45 2021/6/3
 * @Modified By:
 */
public class CompanyCustomerAddInVO implements Serializable {

    /** 公司名称 */
    private String companyName;

    /** 公司简称 */
    private String companyNameShorter;

    /** 客户来源id */
    private Long customerSourceId;

    /** 客户年采购额key */
    private String customerProcurementKey;

    /** 主营产品类别idList */
    private List<Long> mainProductCategoryIdList;

    /** 所属国家区域id */
    private Long regionId;

    /** 所属时区id */
    private Long timeZoneId;

    /** 公司规模key */
    private String companySizeKey;

    /** 客户类别id */
    private Long customerCategoryId;

    /** 客户所属分组id */
    private Long customerGourpId;

    /** 客户所属公海分组id */
    private Long customerPublicGourpId;

    /** 客户状态id */
    private Long customerStatusId;

    /** 客户标签id */
    private Long customerLabelId;

    /** 客户星级Key */
    private String customerStarLevelKey;

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

    /** 联系人列表 */
    private List<CompanyCustomerContactsAddInVO> contactsList;

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

    public String getCustomerProcurementKey() {
        return customerProcurementKey;
    }

    public void setCustomerProcurementKey(String customerProcurementKey) {
        this.customerProcurementKey = customerProcurementKey;
    }

    public List<Long> getMainProductCategoryIdList() {
        return mainProductCategoryIdList;
    }

    public void setMainProductCategoryIdList(List<Long> mainProductCategoryIdList) {
        this.mainProductCategoryIdList = mainProductCategoryIdList;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(Long timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getCompanySizeKey() {
        return companySizeKey;
    }

    public void setCompanySizeKey(String companySizeKey) {
        this.companySizeKey = companySizeKey;
    }

    public Long getCustomerCategoryId() {
        return customerCategoryId;
    }

    public void setCustomerCategoryId(Long customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    public Long getCustomerGourpId() {
        return customerGourpId;
    }

    public void setCustomerGourpId(Long customerGourpId) {
        this.customerGourpId = customerGourpId;
    }

    public Long getCustomerPublicGourpId() {
        return customerPublicGourpId;
    }

    public void setCustomerPublicGourpId(Long customerPublicGourpId) {
        this.customerPublicGourpId = customerPublicGourpId;
    }

    public Long getCustomerStatusId() {
        return customerStatusId;
    }

    public void setCustomerStatusId(Long customerStatusId) {
        this.customerStatusId = customerStatusId;
    }

    public Long getCustomerLabelId() {
        return customerLabelId;
    }

    public void setCustomerLabelId(Long customerLabelId) {
        this.customerLabelId = customerLabelId;
    }

    public String getCustomerStarLevelKey() {
        return customerStarLevelKey;
    }

    public void setCustomerStarLevelKey(String customerStarLevelKey) {
        this.customerStarLevelKey = customerStarLevelKey;
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

    public List<CompanyCustomerContactsAddInVO> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<CompanyCustomerContactsAddInVO> contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public String toString() {
        return "CompanyCustomerAddInVO{" +
                "companyName='" + companyName + '\'' +
                ", companyNameShorter='" + companyNameShorter + '\'' +
                ", customerSourceId=" + customerSourceId +
                ", customerProcurementKey='" + customerProcurementKey + '\'' +
                ", mainProductCategoryIdList=" + mainProductCategoryIdList +
                ", regionId=" + regionId +
                ", timeZoneId=" + timeZoneId +
                ", companySizeKey='" + companySizeKey + '\'' +
                ", customerCategoryId=" + customerCategoryId +
                ", customerGourpId=" + customerGourpId +
                ", customerPublicGourpId=" + customerPublicGourpId +
                ", customerStatusId=" + customerStatusId +
                ", customerLabelId=" + customerLabelId +
                ", customerStarLevelKey='" + customerStarLevelKey + '\'' +
                ", nextFollowTime=" + nextFollowTime +
                ", companyRemarks='" + companyRemarks + '\'' +
                ", regionPhonePrefix='" + regionPhonePrefix + '\'' +
                ", phone='" + phone + '\'' +
                ", companyImageUrl='" + companyImageUrl + '\'' +
                ", companyHomepageUrl='" + companyHomepageUrl + '\'' +
                ", customerFax='" + customerFax + '\'' +
                ", contactsList='" + contactsList + '\'' +
                '}';
    }
}
