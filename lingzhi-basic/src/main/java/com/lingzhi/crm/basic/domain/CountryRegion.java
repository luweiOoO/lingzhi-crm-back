package com.lingzhi.crm.basic.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;

/**
 * 国家地区信息对象 country_region
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public class CountryRegion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 区域英文名称 */
    @Excel(name = "区域英文名称")
    private String regionEnglishName;

    /** 区域中文名称 */
    @Excel(name = "区域中文名称")
    private String regionChineseName;

    /** 区域中英文组合名称 */
    @Excel(name = "区域中英文组合名称")
    private String regionWholeName;

    /** 座机前缀 */
    @Excel(name = "座机前缀")
    private String regionPhonePrefix;

    /** 父级区域id */
    @Excel(name = "父级区域id")
    private Long pRegionId;

    /** 区域层级 */
    @Excel(name = "区域层级")
    private Integer regionLevel;

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
    public void setRegionEnglishName(String regionEnglishName) 
    {
        this.regionEnglishName = regionEnglishName;
    }

    public String getRegionEnglishName() 
    {
        return regionEnglishName;
    }
    public void setRegionChineseName(String regionChineseName) 
    {
        this.regionChineseName = regionChineseName;
    }

    public String getRegionChineseName() 
    {
        return regionChineseName;
    }
    public void setRegionPhonePrefix(String regionPhonePrefix) 
    {
        this.regionPhonePrefix = regionPhonePrefix;
    }

    public String getRegionWholeName() {
        return regionWholeName;
    }

    public void setRegionWholeName(String regionWholeName) {
        this.regionWholeName = regionWholeName;
    }

    public String getRegionPhonePrefix()
    {
        return regionPhonePrefix;
    }
    public void setpRegionId(Long pRegionId) 
    {
        this.pRegionId = pRegionId;
    }

    public Long getpRegionId() 
    {
        return pRegionId;
    }
    public void setRegionLevel(Integer regionLevel) 
    {
        this.regionLevel = regionLevel;
    }

    public Integer getRegionLevel() 
    {
        return regionLevel;
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
            .append("regionEnglishName", getRegionEnglishName())
            .append("regionChineseName", getRegionChineseName())
            .append("regionWholeName",getRegionWholeName())
            .append("regionPhonePrefix", getRegionPhonePrefix())
            .append("pRegionId", getpRegionId())
            .append("regionLevel", getRegionLevel())
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
