package com.lingzhi.crm.basic.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
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
import com.lingzhi.crm.basic.domain.TimeZone;
import com.lingzhi.crm.basic.service.ITimeZoneService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 世界时区信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/basic/timeZone")
public class TimeZoneController extends BaseController
{
    @Autowired
    private ITimeZoneService timeZoneService;

    /**
     * 查询世界时区信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:zone:list')")
    @GetMapping("/list")
    public TableDataInfo list(TimeZone timeZone)
    {
        startPage();
        List<TimeZone> list = timeZoneService.selectTimeZoneList(timeZone);
        return getDataTable(list);
    }

    /**
     * 导出世界时区信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:zone:export')")
    @Log(title = "世界时区信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TimeZone timeZone)
    {
        List<TimeZone> list = timeZoneService.selectTimeZoneList(timeZone);
        ExcelUtil<TimeZone> util = new ExcelUtil<TimeZone>(TimeZone.class);
        return util.exportExcel(list, "世界时区信息数据");
    }

    /**
     * 获取世界时区信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:zone:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(timeZoneService.selectTimeZoneById(id));
    }

    /**
     * 新增世界时区信息
     */
    @PreAuthorize("@ss.hasPermi('system:zone:add')")
    @Log(title = "世界时区信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TimeZone timeZone)
    {
        return toAjax(timeZoneService.insertTimeZone(timeZone));
    }

    /**
     * 修改世界时区信息
     */
    @PreAuthorize("@ss.hasPermi('system:zone:edit')")
    @Log(title = "世界时区信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TimeZone timeZone)
    {
        return toAjax(timeZoneService.updateTimeZone(timeZone));
    }

    /**
     * 删除世界时区信息
     */
    @PreAuthorize("@ss.hasPermi('system:zone:remove')")
    @Log(title = "世界时区信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(timeZoneService.deleteTimeZoneByIds(ids));
    }

    /**
     * 查询世界时区信息列表(下拉框)
     */
    @GetMapping("/selectTimeZoneList")
    public TableDataInfo selectTimeZoneList()
    {
        TimeZone timeZone = new TimeZone();
        List<TimeZone> list = timeZoneService.selectTimeZoneList(timeZone);
        return getDataTable(list);
    }
}
