package com.lingzhi.crm.mail.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;

/**
 * 邮箱账户信息对象 email_account_info
 *
 * @author ruoyi
 * @date 2021-06-17
 */
public class EmailAccountInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long emailId;

    /** 邮箱地址 */
    @Excel(name = "邮箱地址")
    private String emailAddress;

    /** 邮箱密码 */
    @Excel(name = "邮箱密码")
    private String emailPassword;

    /** 邮箱服务器类型 */
    @Excel(name = "邮箱服务器类型")
    private String emailServerType;

    /** 可用标识 1异常 2可用 */
    @Excel(name = "可用标识 1异常 2可用")
    private String enableFlag;

    /** 接收邮件服务器地址 */
    @Excel(name = "接收邮件服务器地址")
    private String receiveHost;

    /** 接收邮件服务器端口号 */
    @Excel(name = "接收邮件服务器端口号")
    private String receivePort;

    /** 接收邮件服务器所用协议 */
    @Excel(name = "接收邮件服务器所用协议")
    private String receiveProtocol;

    /** 接收邮件服务器是否需要SSL */
    @Excel(name = "接收邮件服务器是否需要SSL")
    private String receiveSsl;

    /** 发送邮件服务器端口号 */
    @Excel(name = "发送邮件服务器端口号")
    private String sendPort;

    /** 发送邮件服务器地址 */
    @Excel(name = "发送邮件服务器地址")
    private String sendHost;

    /** 发送邮件服务器是否需要SSL */
    @Excel(name = "发送邮件服务器是否需要SSL")
    private String sendSsl;

    /** 绑定用户id */
    @Excel(name = "绑定用户id")
    private Long userId;

    /** 账号是否可用 1正常2异常 */
    @Excel(name = "账号是否可用 1正常2异常")
    private String validFlag;

    /** 删除标志 0未删除 1已删除 */
    @Excel(name = "删除标志 0未删除 1已删除")
    private String deleteFlag;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 创建人昵称 */
    @Excel(name = "创建人昵称")
    private String createdUserName;

    /** 创建人账号 */
    @Excel(name = "创建人账号")
    private String createdUserAccount;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 更新人昵称 */
    @Excel(name = "更新人昵称")
    private String updatedUserName;

    /** 更新人账号 */
    @Excel(name = "更新人账号")
    private String updatedUserAccount;

    public void setEmailId(Long emailId)
    {
        this.emailId = emailId;
    }

    public Long getEmailId()
    {
        return emailId;
    }
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }
    public void setEmailPassword(String emailPassword)
    {
        this.emailPassword = emailPassword;
    }

    public String getEmailPassword()
    {
        return emailPassword;
    }
    public void setEmailServerType(String emailServerType)
    {
        this.emailServerType = emailServerType;
    }

    public String getEmailServerType()
    {
        return emailServerType;
    }
    public void setEnableFlag(String enableFlag)
    {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag()
    {
        return enableFlag;
    }
    public void setReceiveHost(String receiveHost)
    {
        this.receiveHost = receiveHost;
    }

    public String getReceiveHost()
    {
        return receiveHost;
    }
    public void setReceivePort(String receivePort)
    {
        this.receivePort = receivePort;
    }

    public String getReceivePort()
    {
        return receivePort;
    }
    public void setReceiveProtocol(String receiveProtocol)
    {
        this.receiveProtocol = receiveProtocol;
    }

    public String getReceiveProtocol()
    {
        return receiveProtocol;
    }
    public void setReceiveSsl(String receiveSsl)
    {
        this.receiveSsl = receiveSsl;
    }

    public String getReceiveSsl()
    {
        return receiveSsl;
    }
    public void setSendPort(String sendPort)
    {
        this.sendPort = sendPort;
    }

    public String getSendPort()
    {
        return sendPort;
    }
    public void setSendHost(String sendHost)
    {
        this.sendHost = sendHost;
    }

    public String getSendHost()
    {
        return sendHost;
    }
    public void setSendSsl(String sendSsl)
    {
        this.sendSsl = sendSsl;
    }

    public String getSendSsl()
    {
        return sendSsl;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setValidFlag(String validFlag)
    {
        this.validFlag = validFlag;
    }

    public String getValidFlag()
    {
        return validFlag;
    }
    public void setDeleteFlag(String deleteFlag)
    {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteFlag()
    {
        return deleteFlag;
    }
    public void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime()
    {
        return createdTime;
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
    public void setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("emailId", getEmailId())
                .append("emailAddress", getEmailAddress())
                .append("emailPassword", getEmailPassword())
                .append("emailServerType", getEmailServerType())
                .append("enableFlag", getEnableFlag())
                .append("receiveHost", getReceiveHost())
                .append("receivePort", getReceivePort())
                .append("receiveProtocol", getReceiveProtocol())
                .append("receiveSsl", getReceiveSsl())
                .append("sendPort", getSendPort())
                .append("sendHost", getSendHost())
                .append("sendSsl", getSendSsl())
                .append("userId", getUserId())
                .append("validFlag", getValidFlag())
                .append("deleteFlag", getDeleteFlag())
                .append("createdTime", getCreatedTime())
                .append("createdUserName", getCreatedUserName())
                .append("createdUserAccount", getCreatedUserAccount())
                .append("updatedTime", getUpdatedTime())
                .append("updatedUserName", getUpdatedUserName())
                .append("updatedUserAccount", getUpdatedUserAccount())
                .toString();
    }
}
