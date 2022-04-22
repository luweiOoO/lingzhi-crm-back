package com.lingzhi.crm.web.controller.system;

import java.util.List;

import com.lingzhi.crm.common.annotation.Log;
import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.core.page.TableDataInfo;
import com.lingzhi.crm.common.enums.BusinessType;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
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
import com.lingzhi.crm.system.domain.SysTenant;
import com.lingzhi.crm.system.service.ISysTenantService;

/**
 * 租户信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-10
 */
@RestController
@RequestMapping("/system/tenant")
public class SysTenantController extends BaseController
{
    @Autowired
    private ISysTenantService sysTenantService;

    /**
     * 查询租户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTenant sysTenant)
    {
        startPage();
        List<SysTenant> list = sysTenantService.selectSysTenantList(sysTenant);
        return getDataTable(list);
    }

    /**
     * 导出租户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:export')")
    @Log(title = "租户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysTenant sysTenant)
    {
        List<SysTenant> list = sysTenantService.selectSysTenantList(sysTenant);
        ExcelUtil<SysTenant> util = new ExcelUtil<SysTenant>(SysTenant.class);
        return util.exportExcel(list, "租户信息数据");
    }

    /**
     * 获取租户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:query')")
    @GetMapping(value = "/{tenantId}")
    public AjaxResult getInfo(@PathVariable("tenantId") Long tenantId)
    {
        return AjaxResult.success(sysTenantService.selectSysTenantById(tenantId));
    }

    /**
     * 新增租户信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:add')")
    @Log(title = "租户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTenant sysTenant)
    {
        return toAjax(sysTenantService.insertSysTenant(sysTenant));
    }

    /**
     * 修改租户信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:edit')")
    @Log(title = "租户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTenant sysTenant)
    {
        return toAjax(sysTenantService.updateSysTenant(sysTenant));
    }

    /**
     * 删除租户信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:remove')")
    @Log(title = "租户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tenantIds}")
    public AjaxResult remove(@PathVariable Long[] tenantIds)
    {
        return toAjax(sysTenantService.deleteSysTenantByIds(tenantIds));
    }
}
