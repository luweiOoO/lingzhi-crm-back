package com.lingzhi.crm.mail.mapper;

import java.util.List;
import com.lingzhi.crm.mail.domain.MailContentInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 邮件基础信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-15
 */
public interface MailContentInfoMapper {
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
     * 删除邮件基础信息
     *
     * @param mailId 邮件基础信息ID
     * @return 结果
     */
    public int deleteMailContentInfoById(Long mailId);

    /**
     * 批量删除邮件基础信息
     *
     * @param mailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMailContentInfoByIds(Long[] mailIds);

    /**
     * 批量新增邮件信息
     * @param contentInfoList
     * @return
     */
    int insertMailContentInfoList(@Param("contentInfoList") List<MailContentInfo> contentInfoList);
}
