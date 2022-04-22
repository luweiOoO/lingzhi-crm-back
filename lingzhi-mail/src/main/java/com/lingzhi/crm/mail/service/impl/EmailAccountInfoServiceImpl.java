package com.lingzhi.crm.mail.service.impl;

import java.util.Date;
import java.util.List;

import com.lingzhi.crm.common.core.domain.model.LoginUser;
import com.lingzhi.crm.common.utils.SecurityUtils;
import com.lingzhi.crm.mail.vo.EmailAccountBindChangeInVO;
import com.lingzhi.crm.mail.vo.EmailAccountListOutVO;
import com.lingzhi.crm.mail.vo.EmailAccountTestOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.mail.mapper.EmailAccountInfoMapper;
import com.lingzhi.crm.mail.domain.EmailAccountInfo;
import com.lingzhi.crm.mail.service.IEmailAccountInfoService;
import utils.MailUtils;

/**
 * 邮箱账户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-16
 */
@Service
public class EmailAccountInfoServiceImpl implements IEmailAccountInfoService 
{
    @Autowired
    private EmailAccountInfoMapper emailAccountInfoMapper;

    /**
     * 查询邮箱账户信息
     * 
     * @param emailId 邮箱账户信息ID
     * @return 邮箱账户信息
     */
    @Override
    public EmailAccountInfo selectEmailAccountInfoById(Long emailId)
    {
        return emailAccountInfoMapper.selectEmailAccountInfoById(emailId);
    }

    /**
     * 查询邮箱账户信息列表
     * 
     * @param emailAccountInfo 邮箱账户信息
     * @return 邮箱账户信息
     */
    @Override
    public List<EmailAccountListOutVO> selectEmailAccountInfoList(EmailAccountInfo emailAccountInfo)
    {
        return emailAccountInfoMapper.selectEmailAccountInfoList(emailAccountInfo);
    }

    /**
     * 新增邮箱账户信息
     * 
     * @param emailAccountInfo 邮箱账户信息
     * @return 结果
     */
    @Override
    public int insertEmailAccountInfo(EmailAccountInfo emailAccountInfo)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        emailAccountInfo.setCreatedTime(new Date());
        emailAccountInfo.setCreatedUserAccount(loginUser.getUsername());
        emailAccountInfo.setCreatedUserName(loginUser.getUser().getNickName());
        //TODO 默认配置需进行匹配邮箱服务商的收发邮件服务器
        return emailAccountInfoMapper.insertEmailAccountInfo(emailAccountInfo);
    }

    /**
     * 修改邮箱账户信息
     * 
     * @param emailAccountInfo 邮箱账户信息
     * @return 结果
     */
    @Override
    public int updateEmailAccountInfo(EmailAccountInfo emailAccountInfo)
    {
        return emailAccountInfoMapper.updateEmailAccountInfo(emailAccountInfo);
    }

    /**
     * 批量删除邮箱账户信息
     * 
     * @param emailIds 需要删除的邮箱账户信息ID
     * @return 结果
     */
    @Override
    public int deleteEmailAccountInfoByIds(Long[] emailIds)
    {
        return emailAccountInfoMapper.deleteEmailAccountInfoByIds(emailIds);
    }

    /**
     * 删除邮箱账户信息信息
     * 
     * @param emailId 邮箱账户信息ID
     * @return 结果
     */
    @Override
    public int deleteEmailAccountInfoById(Long emailId)
    {
        return emailAccountInfoMapper.deleteEmailAccountInfoById(emailId);
    }

    @Override
    public int emailAccountBindChange(EmailAccountBindChangeInVO params) {
        return emailAccountInfoMapper.emailAccountBindChange(params);
    }

    @Override
    public EmailAccountTestOutVO emailTestConnect(Long emailId) {
        EmailAccountTestOutVO testOutVO = new EmailAccountTestOutVO();
        EmailAccountInfo accountInfo = selectEmailAccountInfoById(emailId);
        //检测发件服务器信息
        Boolean sendResult = MailUtils.isSendConnect(accountInfo);
        //检测收件服务器信息
        Boolean receiveResult = MailUtils.isReceiveConnect(accountInfo);
        testOutVO.setSendHostResult(sendResult);
        testOutVO.setReceiveHostResult(receiveResult);
        return testOutVO;
    }
}
