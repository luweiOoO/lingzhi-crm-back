package com.lingzhi.crm.mail.mapper;

import java.util.List;
import com.lingzhi.crm.mail.domain.EmailAccountInfo;
import com.lingzhi.crm.mail.vo.EmailAccountBindChangeInVO;
import com.lingzhi.crm.mail.vo.EmailAccountListOutVO;

/**
 * 邮箱账户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-16
 */
public interface EmailAccountInfoMapper 
{
    /**
     * 查询邮箱账户信息
     * 
     * @param emailId 邮箱账户信息ID
     * @return 邮箱账户信息
     */
    public EmailAccountInfo selectEmailAccountInfoById(Long emailId);

    /**
     * 查询邮箱账户信息列表
     * 
     * @param emailAccountInfo 邮箱账户信息
     * @return 邮箱账户信息集合
     */
    public List<EmailAccountListOutVO> selectEmailAccountInfoList(EmailAccountInfo emailAccountInfo);

    /**
     * 新增邮箱账户信息
     * 
     * @param emailAccountInfo 邮箱账户信息
     * @return 结果
     */
    public int insertEmailAccountInfo(EmailAccountInfo emailAccountInfo);

    /**
     * 修改邮箱账户信息
     * 
     * @param emailAccountInfo 邮箱账户信息
     * @return 结果
     */
    public int updateEmailAccountInfo(EmailAccountInfo emailAccountInfo);

    /**
     * 删除邮箱账户信息
     * 
     * @param emailId 邮箱账户信息ID
     * @return 结果
     */
    public int deleteEmailAccountInfoById(Long emailId);

    /**
     * 批量删除邮箱账户信息
     * 
     * @param emailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEmailAccountInfoByIds(Long[] emailIds);

    /**
     * 修改邮箱绑定人
     * @param params
     * @return
     */
    int emailAccountBindChange(EmailAccountBindChangeInVO params);
}
