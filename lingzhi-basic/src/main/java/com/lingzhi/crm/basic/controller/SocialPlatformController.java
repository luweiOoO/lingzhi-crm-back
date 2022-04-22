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
import com.lingzhi.crm.basic.domain.SocialPlatform;
import com.lingzhi.crm.basic.service.ISocialPlatformService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 社交平台信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/basic/social/platform")
public class SocialPlatformController extends BaseController
{
    @Autowired
    private ISocialPlatformService socialPlatformService;

    /**
     * 查询社交平台信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:platform:list')")
    @GetMapping("/list")
    public TableDataInfo list(SocialPlatform socialPlatform)
    {
        startPage();
        List<SocialPlatform> list = socialPlatformService.selectSocialPlatformList(socialPlatform);
        return getDataTable(list);
    }

    /**
     * 导出社交平台信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:platform:export')")
    @Log(title = "社交平台信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SocialPlatform socialPlatform)
    {
        List<SocialPlatform> list = socialPlatformService.selectSocialPlatformList(socialPlatform);
        ExcelUtil<SocialPlatform> util = new ExcelUtil<SocialPlatform>(SocialPlatform.class);
        return util.exportExcel(list, "社交平台信息数据");
    }

    /**
     * 获取社交平台信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:platform:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(socialPlatformService.selectSocialPlatformById(id));
    }

    /**
     * 新增社交平台信息
     */
    @PreAuthorize("@ss.hasPermi('system:platform:add')")
    @Log(title = "社交平台信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SocialPlatform socialPlatform)
    {
        return toAjax(socialPlatformService.insertSocialPlatform(socialPlatform));
    }

    /**
     * 修改社交平台信息
     */
    @PreAuthorize("@ss.hasPermi('system:platform:edit')")
    @Log(title = "社交平台信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SocialPlatform socialPlatform)
    {
        return toAjax(socialPlatformService.updateSocialPlatform(socialPlatform));
    }

    /**
     * 删除社交平台信息
     */
    @PreAuthorize("@ss.hasPermi('system:platform:remove')")
    @Log(title = "社交平台信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(socialPlatformService.deleteSocialPlatformByIds(ids));
    }

    /**
     * 查询社交平台信息列表(下拉框)
     */
    @GetMapping("/selectPlatformList")
    public TableDataInfo list()
    {
        SocialPlatform socialPlatform = new SocialPlatform();
        List<SocialPlatform> list = socialPlatformService.selectSocialPlatformList(socialPlatform);
        return getDataTable(list);
    }
}
