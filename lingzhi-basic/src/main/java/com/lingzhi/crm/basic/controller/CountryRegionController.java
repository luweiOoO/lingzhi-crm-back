package com.lingzhi.crm.basic.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lingzhi.crm.common.annotation.Log;
import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.enums.BusinessType;
import com.lingzhi.crm.basic.domain.CountryRegion;
import com.lingzhi.crm.basic.service.ICountryRegionService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 国家地区信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/basic/region")
public class CountryRegionController extends BaseController
{
    @Autowired
    private ICountryRegionService countryRegionService;

    /**
     * 查询国家地区信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:region:list')")
    @GetMapping("/list")
    public TableDataInfo list(CountryRegion countryRegion)
    {
        startPage();
        List<CountryRegion> list = countryRegionService.selectCountryRegionList(countryRegion);
        return getDataTable(list);
    }

    /**
     * 导出国家地区信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:region:export')")
    @Log(title = "国家地区信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CountryRegion countryRegion)
    {
        List<CountryRegion> list = countryRegionService.selectCountryRegionList(countryRegion);
        ExcelUtil<CountryRegion> util = new ExcelUtil<CountryRegion>(CountryRegion.class);
        return util.exportExcel(list, "国家地区信息数据");
    }

    /**
     * 获取国家地区信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:region:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(countryRegionService.selectCountryRegionById(id));
    }

    /**
     * 新增国家地区信息
     */
    @PreAuthorize("@ss.hasPermi('system:region:add')")
    @Log(title = "国家地区信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CountryRegion countryRegion)
    {
        return toAjax(countryRegionService.insertCountryRegion(countryRegion));
    }

    /**
     * 修改国家地区信息
     */
    @PreAuthorize("@ss.hasPermi('system:region:edit')")
    @Log(title = "国家地区信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CountryRegion countryRegion)
    {
        return toAjax(countryRegionService.updateCountryRegion(countryRegion));
    }

    /**
     * 删除国家地区信息
     */
    @PreAuthorize("@ss.hasPermi('system:region:remove')")
    @Log(title = "国家地区信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(countryRegionService.deleteCountryRegionByIds(ids));
    }

    /**
     * 查询国家地区信息列表（选择时使用）
     */
    @GetMapping("/selectList")
    public TableDataInfo selectRegionList()
    {
        CountryRegion countryRegion = new CountryRegion();
        List<CountryRegion> list = countryRegionService.selectCountryRegionList(countryRegion);
        return getDataTable(list);
    }
}
