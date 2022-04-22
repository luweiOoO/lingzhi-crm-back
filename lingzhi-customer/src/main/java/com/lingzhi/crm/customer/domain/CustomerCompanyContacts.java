package com.lingzhi.crm.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;

/**
 * 客户公司联系人信息对象 customer_company_contacts
 * 
 * @author ruoyi
 * @date 2021-06-03
 */
public class CustomerCompanyContacts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 所属公司id */
    @Excel(name = "所属公司id")
    private Long customerCompanyId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String contactNickName;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String contactEmail;

    /** 职级key */
    @Excel(name = "职级key")
    private String contactRankKey;

    /** 职位 */
    @Excel(name = "职位")
    private String contactPosition;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contactBirthday;

    /** 性别key */
    @Excel(name = "性别key")
    private String contactGenderKey;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imageUrl;

    /** 联系人住址 */
    @Excel(name = "联系人住址")
    private String contactAddress;

    /** 联系人邮箱或电话 */
    @Excel(name = "联系人邮箱或电话")
    private String contactEamilPhone;

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

    /** 所属租户id */
    @Excel(name = "所属租户id")
    private Long tenantId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerCompanyId(Long customerCompanyId) 
    {
        this.customerCompanyId = customerCompanyId;
    }

    public Long getCustomerCompanyId() 
    {
        return customerCompanyId;
    }
    public void setContactNickName(String contactNickName) 
    {
        this.contactNickName = contactNickName;
    }

    public String getContactNickName() 
    {
        return contactNickName;
    }
    public void setContactEmail(String contactEmail) 
    {
        this.contactEmail = contactEmail;
    }

    public String getContactEmail() 
    {
        return contactEmail;
    }
    public void setContactRankKey(String contactRankKey) 
    {
        this.contactRankKey = contactRankKey;
    }

    public String getContactRankKey() 
    {
        return contactRankKey;
    }
    public void setContactPosition(String contactPosition) 
    {
        this.contactPosition = contactPosition;
    }

    public String getContactPosition() 
    {
        return contactPosition;
    }
    public void setContactBirthday(Date contactBirthday) 
    {
        this.contactBirthday = contactBirthday;
    }

    public Date getContactBirthday() 
    {
        return contactBirthday;
    }
    public void setContactGenderKey(String contactGenderKey) 
    {
        this.contactGenderKey = contactGenderKey;
    }

    public String getContactGenderKey() 
    {
        return contactGenderKey;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }
    public void setContactAddress(String contactAddress) 
    {
        this.contactAddress = contactAddress;
    }

    public String getContactAddress() 
    {
        return contactAddress;
    }
    public void setContactEamilPhone(String contactEamilPhone) 
    {
        this.contactEamilPhone = contactEamilPhone;
    }

    public String getContactEamilPhone() 
    {
        return contactEamilPhone;
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
    public void setTenantId(Long tenantId) 
    {
        this.tenantId = tenantId;
    }

    public Long getTenantId() 
    {
        return tenantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerCompanyId", getCustomerCompanyId())
            .append("contactNickName", getContactNickName())
            .append("contactEmail", getContactEmail())
            .append("contactRankKey", getContactRankKey())
            .append("contactPosition", getContactPosition())
            .append("contactBirthday", getContactBirthday())
            .append("contactGenderKey", getContactGenderKey())
            .append("remarks", getRemarks())
            .append("imageUrl", getImageUrl())
            .append("contactAddress", getContactAddress())
            .append("contactEamilPhone", getContactEamilPhone())
            .append("createdUserName", getCreatedUserName())
            .append("createdUserAccount", getCreatedUserAccount())
            .append("createdTime", getCreatedTime())
            .append("updatedUserName", getUpdatedUserName())
            .append("updatedUserAccount", getUpdatedUserAccount())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .append("tenantId", getTenantId())
            .toString();
    }
}
