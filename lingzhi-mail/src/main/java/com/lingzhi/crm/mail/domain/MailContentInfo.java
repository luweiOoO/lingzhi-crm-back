package com.lingzhi.crm.mail.domain;

import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 邮件基础信息对象 mail_content_info
 * 
 * @author ruoyi
 * @date 2021-06-15
 */
public class MailContentInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 邮件id */
    private Long mailId;

    /** 邮件类型 0草稿 1收件 2发件 */
    @Excel(name = "邮件类型 0草稿 1收件 2发件")
    private String mailType;

    /** 邮件内容 */
    @Excel(name = "邮件内容")
    private String mailPlainText;

    /** 邮件主题 */
    @Excel(name = "邮件主题")
    private String mailSubject;

    /** 邮件发送人 */
    @Excel(name = "邮件发送人")
    private String mailSender;

    /** 邮件唯一标识id */
    @Excel(name = "邮件唯一标识id")
    private String mailMessageId;

    /** 邮件接收人 */
    @Excel(name = "邮件接收人")
    private String receiver;

    /** 是否已读 0未读 1已读 */
    @Excel(name = "是否已读 0未读 1已读")
    private String readFlag;

    /** 发送状态 0未发送 1已发送 */
    @Excel(name = "发送状态 0未发送 1已发送")
    private String sendStatus;

    /** 发送时间 */
    @Excel(name = "发送时间")
    private Date sendDate;

    /** 接收时间 */
    @Excel(name = "接收时间")
    private Date receivedDate;

    /** 所属文件夹id */
    @Excel(name = "所属文件夹id")
    private Long folderId;

    /** 浏览次数 */
    @Excel(name = "浏览次数")
    private Integer viewCount;

    /** 删除标志 0未删除 1已首次删除 2已彻底删除 */
    @Excel(name = "删除标志 0未删除 1已首次删除 2已彻底删除")
    private String deleteFlag;

    public void setMailId(Long mailId) 
    {
        this.mailId = mailId;
    }

    public Long getMailId() 
    {
        return mailId;
    }
    public void setMailType(String mailType) 
    {
        this.mailType = mailType;
    }

    public String getMailType() 
    {
        return mailType;
    }
    public void setMailPlainText(String mailPlainText) 
    {
        this.mailPlainText = mailPlainText;
    }

    public String getMailPlainText() 
    {
        return mailPlainText;
    }
    public void setMailSubject(String mailSubject) 
    {
        this.mailSubject = mailSubject;
    }

    public String getMailSubject() 
    {
        return mailSubject;
    }
    public void setMailSender(String mailSender) 
    {
        this.mailSender = mailSender;
    }

    public String getMailSender() 
    {
        return mailSender;
    }
    public void setReadFlag(String readFlag) 
    {
        this.readFlag = readFlag;
    }

    public String getReadFlag() 
    {
        return readFlag;
    }
    public void setSendStatus(String sendStatus) 
    {
        this.sendStatus = sendStatus;
    }

    public String getSendStatus() 
    {
        return sendStatus;
    }
    public void setFolderId(Long folderId) 
    {
        this.folderId = folderId;
    }

    public Long getFolderId() 
    {
        return folderId;
    }
    public void setViewCount(Integer viewCount) 
    {
        this.viewCount = viewCount;
    }

    public Integer getViewCount() 
    {
        return viewCount;
    }
    public void setDeleteFlag(String deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public String getDeleteFlag() 
    {
        return deleteFlag;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getMailMessageId() {
        return mailMessageId;
    }

    public void setMailMessageId(String mailMessageId) {
        this.mailMessageId = mailMessageId;
    }

    @Override
    public String toString() {
        return "MailContentInfo{" +
                "mailId=" + mailId +
                ", mailType='" + mailType + '\'' +
                ", mailPlainText='" + mailPlainText + '\'' +
                ", mailSubject='" + mailSubject + '\'' +
                ", mailSender='" + mailSender + '\'' +
                ", mailMessageId='" + mailMessageId + '\'' +
                ", receiver='" + receiver + '\'' +
                ", readFlag='" + readFlag + '\'' +
                ", sendStatus='" + sendStatus + '\'' +
                ", sendDate=" + sendDate +
                ", receivedDate=" + receivedDate +
                ", folderId=" + folderId +
                ", viewCount=" + viewCount +
                ", deleteFlag='" + deleteFlag + '\'' +
                '}';
    }
}
