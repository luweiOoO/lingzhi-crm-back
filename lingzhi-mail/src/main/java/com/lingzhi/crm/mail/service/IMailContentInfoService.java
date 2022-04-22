package com.lingzhi.crm.mail.service;

import java.util.List;

import com.lingzhi.crm.mail.domain.EmailAccountInfo;
import com.lingzhi.crm.mail.domain.MailContentInfo;

import javax.mail.Store;

/**
 * 邮件基础信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-15
 */
public interface IMailContentInfoService 
{
    /**
     * 查询邮件基础信息
     * 
     * @param mailId 邮件基础信息ID
     * @return 邮件基础信息
     */
    public MailContentInfo selectMailContentInfoById(Long mailId);

    /**
     * 查询邮件基础信息列表
     * 
     * @param mailContentInfo 邮件基础信息
     * @return 邮件基础信息集合
     */
    public List<MailContentInfo> selectMailContentInfoList(MailContentInfo mailContentInfo);

    /**
     * 新增邮件基础信息
     * 
     * @param mailContentInfo 邮件基础信息
     * @return 结果
     */
    public int insertMailContentInfo(MailContentInfo mailContentInfo);

    /**
     * 修改邮件基础信息
     * 
     * @param mailContentInfo 邮件基础信息
     * @return 结果
     */
    public int updateMailContentInfo(MailContentInfo mailContentInfo);

    /**
     * 批量删除邮件基础信息
     * 
     * @param mailIds 需要删除的邮件基础信息ID
     * @return 结果
     */
    public int deleteMailContentInfoByIds(Long[] mailIds);

    /**
     * 删除邮件基础信息信息
     * 
     * @param mailId 邮件基础信息ID
     * @return 结果
     */
    public int deleteMailContentInfoById(Long mailId);

    /**
     * 更新并保存指定邮箱账号的邮件
     * @param accountInfoList 邮箱账户列表
     * @return
     */
    int updateAndSaveMail(List<EmailAccountInfo> accountInfoList);

    /**
     * 根据邮箱账户获取该账户的所有邮件信息
     * @param accountInfo 邮箱账户信息
     * @return
     */
    List<MailContentInfo> getMailListByAccount(EmailAccountInfo accountInfo);

    /**
     * 接收所有邮件并更新数据库
     * @return
     */
    int receiveMail();
}
