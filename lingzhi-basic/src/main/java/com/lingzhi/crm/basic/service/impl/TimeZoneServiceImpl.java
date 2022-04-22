package com.lingzhi.crm.basic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.basic.mapper.TimeZoneMapper;
import com.lingzhi.crm.basic.domain.TimeZone;
import com.lingzhi.crm.basic.service.ITimeZoneService;

/**
 * 世界时区信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class TimeZoneServiceImpl implements ITimeZoneService 
{
    @Autowired
    private TimeZoneMapper timeZoneMapper;

    /**
     * 查询世界时区信息
     * 
     * @param id 世界时区信息ID
     * @return 世界时区信息
     */
    @Override
    public TimeZone selectTimeZoneById(Long id)
    {
        return timeZoneMapper.selectTimeZoneById(id);
    }

    /**
     * 查询世界时区信息列表
     * 
     * @param timeZone 世界时区信息
     * @return 世界时区信息
     */
    @Override
    public List<TimeZone> selectTimeZoneList(TimeZone timeZone)
    {
        return timeZoneMapper.selectTimeZoneList(timeZone);
    }

    /**
     * 新增世界时区信息
     * 
     * @param timeZone 世界时区信息
     * @return 结果
     */
    @Override
    public int insertTimeZone(TimeZone timeZone)
    {
        return timeZoneMapper.insertTimeZone(timeZone);
    }

    /**
     * 修改世界时区信息
     * 
     * @param timeZone 世界时区信息
     * @return 结果
     */
    @Override
    public int updateTimeZone(TimeZone timeZone)
    {
        return timeZoneMapper.updateTimeZone(timeZone);
    }

    /**
     * 批量删除世界时区信息
     * 
     * @param ids 需要删除的世界时区信息ID
     * @return 结果
     */
    @Override
    public int deleteTimeZoneByIds(Long[] ids)
    {
        return timeZoneMapper.deleteTimeZoneByIds(ids);
    }

    /**
     * 删除世界时区信息信息
     * 
     * @param id 世界时区信息ID
     * @return 结果
     */
    @Override
    public int deleteTimeZoneById(Long id)
    {
        return timeZoneMapper.deleteTimeZoneById(id);
    }
}
