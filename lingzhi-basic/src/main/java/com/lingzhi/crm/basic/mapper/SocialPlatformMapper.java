package com.lingzhi.crm.basic.mapper;

import java.util.List;
import com.lingzhi.crm.basic.domain.SocialPlatform;

/**
 * 社交平台信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface SocialPlatformMapper 
{
    /**
     * 查询社交平台信息
     * 
     * @param id 社交平台信息ID
     * @return 社交平台信息
     */
    public SocialPlatform selectSocialPlatformById(Long id);

    /**
     * 查询社交平台信息列表
     * 
     * @param socialPlatform 社交平台信息
     * @return 社交平台信息集合
     */
    public List<SocialPlatform> selectSocialPlatformList(SocialPlatform socialPlatform);

    /**
     * 新增社交平台信息
     * 
     * @param socialPlatform 社交平台信息
     * @return 结果
     */
    public int insertSocialPlatform(SocialPlatform socialPlatform);

    /**
     * 修改社交平台信息
     * 
     * @param socialPlatform 社交平台信息
     * @return 结果
     */
    public int updateSocialPlatform(SocialPlatform socialPlatform);

    /**
     * 删除社交平台信息
     * 
     * @param id 社交平台信息ID
     * @return 结果
     */
    public int deleteSocialPlatformById(Long id);

    /**
     * 批量删除社交平台信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSocialPlatformByIds(Long[] ids);
}
