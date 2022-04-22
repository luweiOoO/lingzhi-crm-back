package com.lingzhi.crm.basic.service;

import java.util.List;
import com.lingzhi.crm.basic.domain.TimeZone;

/**
 * 世界时区信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface ITimeZoneService 
{
    /**
     * 查询世界时区信息
     * 
     * @param id 世界时区信息ID
     * @return 世界时区信息
     */
    public TimeZone selectTimeZoneById(Long id);

    /**
     * 查询世界时区信息列表
     * 
     * @param timeZone 世界时区信息
     * @return 世界时区信息集合
     */
    public List<TimeZone> selectTimeZoneList(TimeZone timeZone);

    /**
     * 新增世界时区信息
     * 
     * @param timeZone 世界时区信息
     * @return 结果
     */
    public int insertTimeZone(TimeZone timeZone);

    /**
     * 修改世界时区信息
     * 
     * @param timeZone 世界时区信息
     * @return 结果
     */
    public int updateTimeZone(TimeZone timeZone);

    /**
     * 批量删除世界时区信息
     * 
     * @param ids 需要删除的世界时区信息ID
     * @return 结果
     */
    public int deleteTimeZoneByIds(Long[] ids);

    /**
     * 删除世界时区信息信息
     * 
     * @param id 世界时区信息ID
     * @return 结果
     */
    public int deleteTimeZoneById(Long id);
}
