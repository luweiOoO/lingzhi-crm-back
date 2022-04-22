package com.lingzhi.crm.basic.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;

/**
 * 产品类别信息对象 product_category
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public class ProductCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /** 父级类别id */
    @Excel(name = "父级类别id")
    private Long pCategoryId;

    /** 类别层级 */
    @Excel(name = "类别层级")
    private Integer categoryLevel;

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

    /** 子类别 */
    private List<ProductCategory> children;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setpCategoryId(Long pCategoryId) 
    {
        this.pCategoryId = pCategoryId;
    }

    public Long getpCategoryId() 
    {
        return pCategoryId;
    }
    public void setCategoryLevel(Integer categoryLevel) 
    {
        this.categoryLevel = categoryLevel;
    }

    public Integer getCategoryLevel() 
    {
        return categoryLevel;
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

    public List<ProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategory> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryName", getCategoryName())
            .append("pCategoryId", getpCategoryId())
            .append("categoryLevel", getCategoryLevel())
            .append("createdUserName", getCreatedUserName())
            .append("createdUserAccount", getCreatedUserAccount())
            .append("createdTime", getCreatedTime())
            .append("updatedUserName", getUpdatedUserName())
            .append("updatedUserAccount", getUpdatedUserAccount())
            .append("updatedTime", getUpdatedTime())
            .append("isDelete", getIsDelete())
            .append("tenantId", getTenantId())
            .append("children",getChildren())
            .toString();
    }
}
