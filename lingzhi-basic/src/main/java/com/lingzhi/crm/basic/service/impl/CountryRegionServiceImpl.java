package com.lingzhi.crm.basic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingzhi.crm.basic.mapper.CountryRegionMapper;
import com.lingzhi.crm.basic.domain.CountryRegion;
import com.lingzhi.crm.basic.service.ICountryRegionService;

/**
 * 国家地区信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@Service
public class CountryRegionServiceImpl implements ICountryRegionService 
{
    @Autowired
    private CountryRegionMapper countryRegionMapper;

    /**
     * 查询国家地区信息
     * 
     * @param id 国家地区信息ID
     * @return 国家地区信息
     */
    @Override
    public CountryRegion selectCountryRegionById(Long id)
    {
        return countryRegionMapper.selectCountryRegionById(id);
    }

    /**
     * 查询国家地区信息列表
     * 
     * @param countryRegion 国家地区信息
     * @return 国家地区信息
     */
    @Override
    public List<CountryRegion> selectCountryRegionList(CountryRegion countryRegion)
    {
        return countryRegionMapper.selectCountryRegionList(countryRegion);
    }

    /**
     * 新增国家地区信息
     * 
     * @param countryRegion 国家地区信息
     * @return 结果
     */
    @Override
    public int insertCountryRegion(CountryRegion countryRegion)
    {
        return countryRegionMapper.insertCountryRegion(countryRegion);
    }

    /**
     * 修改国家地区信息
     * 
     * @param countryRegion 国家地区信息
     * @return 结果
     */
    @Override
    public int updateCountryRegion(CountryRegion countryRegion)
    {
        return countryRegionMapper.updateCountryRegion(countryRegion);
    }

    /**
     * 批量删除国家地区信息
     * 
     * @param ids 需要删除的国家地区信息ID
     * @return 结果
     */
    @Override
    public int deleteCountryRegionByIds(Long[] ids)
    {
        return countryRegionMapper.deleteCountryRegionByIds(ids);
    }

    /**
     * 删除国家地区信息信息
     * 
     * @param id 国家地区信息ID
     * @return 结果
     */
    @Override
    public int deleteCountryRegionById(Long id)
    {
        return countryRegionMapper.deleteCountryRegionById(id);
    }
}
