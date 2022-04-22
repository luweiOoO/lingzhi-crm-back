package com.lingzhi.crm.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 租户信息对象 sys_tenant
 *
 * @author ruoyi
 * @date 2021-06-10
 */
public class SysTenant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 租户id */
    private Long tenantId;

    /** 租户名称 */
    @Excel(name = "租户名称")
    private String tenantName;

    /** 租户有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "租户有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validTime;

    /** 租户所有人账号id */
    @Excel(name = "租户所有人账号id")
    private Long ownerUserId;

    /** 删除标志 0未删除 1已删除 */
    @Excel(name = "删除标志 0未删除 1已删除")
    private Integer isDelete;

    /** 租户状态 0启用 1停用 */
    @Excel(name = "租户状态 0启用 1停用")
    private Integer status;

    public void setTenantId(Long tenantId)
    {
        this.tenantId = tenantId;
    }

    public Long getTenantId()
    {
        return tenantId;
    }
    public void setTenantName(String tenantName)
    {
        this.tenantName = tenantName;
    }

    public String getTenantName()
    {
        return tenantName;
    }
    public void setValidTime(Date validTime)
    {
        this.validTime = validTime;
    }

    public Date getValidTime()
    {
        return validTime;
    }
    public void setOwnerUserId(Long ownerUserId)
    {
        this.ownerUserId = ownerUserId;
    }

    public Long getOwnerUserId()
    {
        return ownerUserId;
    }
    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("tenantId", getTenantId())
                .append("tenantName", getTenantName())
                .append("validTime", getValidTime())
                .append("ownerUserId", getOwnerUserId())
                .append("isDelete", getIsDelete())
                .append("status", getStatus())
                .toString();
    }
}
