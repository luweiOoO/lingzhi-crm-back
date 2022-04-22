package com.lingzhi.crm.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;

/**
 * 客户公司基础信息对象 customer_company_info
 * 
 * @author ruoyi
 * @date 2021-06-03
 */
public class CustomerCompanyInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String customerCode;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 公司简称 */
    @Excel(name = "公司简称")
    private String companyNameShorter;

    /** 客户来源id */
    @Excel(name = "客户来源id")
    private Long customerSourceId;

    /** 客户年采购额key */
    @Excel(name = "客户年采购额key")
    private String customerProcurementKey;

    /** 客户采购意向key */
    @Excel(name = "客户采购意向key")
    private String purchaseIntentionKey;

    /** 所属国家区域id */
    @Excel(name = "所属国家区域id")
    private Long regionId;

    /** 所属时区id */
    @Excel(name = "所属时区id")
    private Long timeZoneId;

    /** 公司规模key */
    @Excel(name = "公司规模key")
    private String companySizeKey;

    /** 客户类别id */
    @Excel(name = "客户类别id")
    private Long customerCategoryId;

    /** 客户所属分组id */
    @Excel(name = "客户所属分组id")
    private Long customerGourpId;

    /** 客户所属公海分组id */
    @Excel(name = "客户所属公海分组id")
    private Long customerPublicGourpId;

    /** 客户状态id */
    @Excel(name = "客户状态id")
    private Long customerStatusId;

    /** 客户标签id */
    @Excel(name = "客户标签id")
    private Long customerLabelId;

    /** 客户星级key */
    @Excel(name = "客户星级key")
    private String customerStarLevelKey;

    /** 下次跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextFollowTime;

    /** 备注 */
    @Excel(name = "备注")
    private String companyRemarks;

    /** 座机前缀 */
    @Excel(name = "座机前缀")
    private String regionPhonePrefix;

    /** 座机 */
    @Excel(name = "座机")
    private String phone;

    /** 图片 */
    @Excel(name = "图片")
    private String companyImageUrl;

    /** 主页地址 */
    @Excel(name = "主页地址")
    private String companyHomepageUrl;

    /** 传真 */
    @Excel(name = "传真")
    private String customerFax;

    /** 所属租户id */
    @Excel(name = "所属租户id")
    private Long tenantId;

    /** 创建人昵称 */
    @Excel(name = "创建人昵称")
    private String createdUserName;

    /** 创建人账号 */
    @Excel(name = "创建人账号")
    private String createdUserAccount;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人昵称 */
    @Excel(name = "更新人昵称")
    private String updatedUserName;

    /** 更新人账号 */
    @Excel(name = "更新人账号")
    private String updatedUserAccount;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 删除标志 0未删除 1已删除 */
    @Excel(name = "删除标志 0未删除 1已删除")
    private Integer isDelete;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerCode(String customerCode) 
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() 
    {
        return customerCode;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setCompanyNameShorter(String companyNameShorter) 
    {
        this.companyNameShorter = companyNameShorter;
    }

    public String getCompanyNameShorter() 
    {
        return companyNameShorter;
    }
    public void setCustomerSourceId(Long customerSourceId) 
    {
        this.customerSourceId = customerSourceId;
    }

    public Long getCustomerSourceId() 
    {
        return customerSourceId;
    }
    public void setCustomerProcurementKey(String customerProcurementKey) 
    {
        this.customerProcurementKey = customerProcurementKey;
    }

    public String getCustomerProcurementKey() 
    {
        return customerProcurementKey;
    }
    public void setPurchaseIntentionKey(String purchaseIntentionKey)
    {
        this.purchaseIntentionKey = purchaseIntentionKey;
    }

    public String getPurchaseIntentionKey()
    {
        return purchaseIntentionKey;
    }
    public void setRegionId(Long regionId) 
    {
        this.regionId = regionId;
    }

    public Long getRegionId() 
    {
        return regionId;
    }
    public void setTimeZoneId(Long timeZoneId) 
    {
        this.timeZoneId = timeZoneId;
    }

    public Long getTimeZoneId() 
    {
        return timeZoneId;
    }

    public String getCompanySizeKey() {
        return companySizeKey;
    }

    public void setCompanySizeKey(String companySizeKey) {
        this.companySizeKey = companySizeKey;
    }

    public void setCustomerCategoryId(Long customerCategoryId)
    {
        this.customerCategoryId = customerCategoryId;
    }

    public Long getCustomerCategoryId() 
    {
        return customerCategoryId;
    }
    public void setCustomerGourpId(Long customerGourpId) 
    {
        this.customerGourpId = customerGourpId;
    }

    public Long getCustomerGourpId() 
    {
        return customerGourpId;
    }
    public void setCustomerPublicGourpId(Long customerPublicGourpId) 
    {
        this.customerPublicGourpId = customerPublicGourpId;
    }

    public Long getCustomerPublicGourpId() 
    {
        return customerPublicGourpId;
    }
    public void setCustomerStatusId(Long customerStatusId) 
    {
        this.customerStatusId = customerStatusId;
    }

    public Long getCustomerStatusId() 
    {
        return customerStatusId;
    }
    public void setCustomerLabelId(Long customerLabelId) 
    {
        this.customerLabelId = customerLabelId;
    }

    public Long getCustomerLabelId() 
    {
        return customerLabelId;
    }
    public void setCustomerStarLevelKey(String customerStarLevelKey) 
    {
        this.customerStarLevelKey = customerStarLevelKey;
    }

    public String getCustomerStarLevelKey() 
    {
        return customerStarLevelKey;
    }
    public void setNextFollowTime(Date nextFollowTime) 
    {
        this.nextFollowTime = nextFollowTime;
    }

    public Date getNextFollowTime() 
    {
        return nextFollowTime;
    }
    public void setCompanyRemarks(String companyRemarks) 
    {
        this.companyRemarks = companyRemarks;
    }

    public String getCompanyRemarks() 
    {
        return companyRemarks;
    }
    public void setRegionPhonePrefix(String regionPhonePrefix) 
    {
        this.regionPhonePrefix = regionPhonePrefix;
    }

    public String getRegionPhonePrefix() 
    {
        return regionPhonePrefix;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setCompanyImageUrl(String companyImageUrl) 
    {
        this.companyImageUrl = companyImageUrl;
    }

    public String getCompanyImageUrl() 
    {
        return companyImageUrl;
    }
    public void setCompanyHomepageUrl(String companyHomepageUrl) 
    {
        this.companyHomepageUrl = companyHomepageUrl;
    }

    public String getCompanyHomepageUrl() 
    {
        return companyHomepageUrl;
    }
    public void setCustomerFax(String customerFax) 
    {
        this.customerFax = customerFax;
    }

    public String getCustomerFax() 
    {
        return customerFax;
    }
    public void setTenantId(Long tenantId) 
    {
        this.tenantId = tenantId;
    }

    public Long getTenantId() 
    {
        return tenantId;
    }
    public void setCreatedUserName(String createdUserName) 
    {
        this.createdUserName = createdUserName;
    }

    public String getCreatedUserName() 
    {
        return createdUserName;
    }
    public void setCreatedUserAccount(String createdUserAccount) 
    {
        this.createdUserAccount = createdUserAccount;
    }

    public String getCreatedUserAccount() 
    {
        return createdUserAccount;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedUserName(String updatedUserName) 
    {
        this.updatedUserName = updatedUserName;
    }

    public String getUpdatedUserName() 
    {
        return updatedUserName;
    }
    public void setUpdatedUserAccount(String updatedUserAccount) 
    {
        this.updatedUserAccount = updatedUserAccount;
    }

    public String getUpdatedUserAccount() 
    {
        return updatedUserAccount;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerCode", getCustomerCode())
            .append("companyName", getCompanyName())
            .append("companyNameShorter", getCompanyNameShorter())
            .append("customerSourceId", getCustomerSourceId())
            .append("customerProcurementKey", getCustomerProcurementKey())
            .append("purchaseIntentionKey", getPurchaseIntentionKey())
            .append("regionId", getRegionId())
            .append("timeZoneId", getTimeZoneId())
            .append("companySizeKey", getCompanySizeKey())
            .append("customerCategoryId", getCustomerCategoryId())
            .append("customerGourpId", getCustomerGourpId())
            .append("customerPublicGourpId", getCustomerPublicGourpId())
            .append("customerStatusId", getCustomerStatusId())
            .append("customerLabelId", getCustomerLabelId())
            .append("customerStarLevelKey", getCustomerStarLevelKey())
            .append("nextFollowTime", getNextFollowTime())
            .append("companyRemarks", getCompanyRemarks())
            .append("regionPhonePrefix", getRegionPhonePrefix())
            .append("phone", getPhone())
            .append("companyImageUrl", getCompanyImageUrl())
            .append("companyHomepageUrl", getCompanyHomepageUrl())
            .append("customerFax", getCustomerFax())
            .append("tenantId", getTenantId())
            .append("createdUserName", getCreatedUserName())
            .append("createdUserAccount", getCreatedUserAccount())
            .append("createdTime", getCreatedTime())
            .append("updatedUserName", getUpdatedUserName())
            .append("updatedUserAccount", getUpdatedUserAccount())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
