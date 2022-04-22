package com.lingzhi.crm.basic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.basic.mapper.SocialPlatformMapper;
import com.lingzhi.crm.basic.domain.SocialPlatform;
import com.lingzhi.crm.basic.service.ISocialPlatformService;

/**
 * 社交平台信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class SocialPlatformServiceImpl implements ISocialPlatformService 
{
    @Autowired
    private SocialPlatformMapper socialPlatformMapper;

    /**
     * 查询社交平台信息
     * 
     * @param id 社交平台信息ID
     * @return 社交平台信息
     */
    @Override
    public SocialPlatform selectSocialPlatformById(Long id)
    {
        return socialPlatformMapper.selectSocialPlatformById(id);
    }

    /**
     * 查询社交平台信息列表
     * 
     * @param socialPlatform 社交平台信息
     * @return 社交平台信息
     */
    @Override
    public List<SocialPlatform> selectSocialPlatformList(SocialPlatform socialPlatform)
    {
        return socialPlatformMapper.selectSocialPlatformList(socialPlatform);
    }

    /**
     * 新增社交平台信息
     * 
     * @param socialPlatform 社交平台信息
     * @return 结果
     */
    @Override
    public int insertSocialPlatform(SocialPlatform socialPlatform)
    {
        return socialPlatformMapper.insertSocialPlatform(socialPlatform);
    }

    /**
     * 修改社交平台信息
     * 
     * @param socialPlatform 社交平台信息
     * @return 结果
     */
    @Override
    public int updateSocialPlatform(SocialPlatform socialPlatform)
    {
        return socialPlatformMapper.updateSocialPlatform(socialPlatform);
    }

    /**
     * 批量删除社交平台信息
     * 
     * @param ids 需要删除的社交平台信息ID
     * @return 结果
     */
    @Override
    public int deleteSocialPlatformByIds(Long[] ids)
    {
        return socialPlatformMapper.deleteSocialPlatformByIds(ids);
    }

    /**
     * 删除社交平台信息信息
     * 
     * @param id 社交平台信息ID
     * @return 结果
     */
    @Override
    public int deleteSocialPlatformById(Long id)
    {
        return socialPlatformMapper.deleteSocialPlatformById(id);
    }
}
