package com.lingzhi.crm.basic.service;

import java.util.List;
import com.lingzhi.crm.basic.domain.CountryRegion;

/**
 * 国家地区信息Service接口
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
public interface ICountryRegionService 
{
    /**
     * 查询国家地区信息
     * 
     * @param id 国家地区信息ID
     * @return 国家地区信息
     */
    public CountryRegion selectCountryRegionById(Long id);

    /**
     * 查询国家地区信息列表
     * 
     * @param countryRegion 国家地区信息
     * @return 国家地区信息集合
     */
    public List<CountryRegion> selectCountryRegionList(CountryRegion countryRegion);

    /**
     * 新增国家地区信息
     * 
     * @param countryRegion 国家地区信息
     * @return 结果
     */
    public int insertCountryRegion(CountryRegion countryRegion);

    /**
     * 修改国家地区信息
     * 
     * @param countryRegion 国家地区信息
     * @return 结果
     */
    public int updateCountryRegion(CountryRegion countryRegion);

    /**
     * 批量删除国家地区信息
     * 
     * @param ids 需要删除的国家地区信息ID
     * @return 结果
     */
    public int deleteCountryRegionByIds(Long[] ids);

    /**
     * 删除国家地区信息信息
     * 
     * @param id 国家地区信息ID
     * @return 结果
     */
    public int deleteCountryRegionById(Long id);
}
