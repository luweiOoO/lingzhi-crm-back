package com.lingzhi.crm.customer.controller;

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
import com.lingzhi.crm.customer.domain.CustomerContactsSocialPlatform;
import com.lingzhi.crm.customer.service.ICustomerContactsSocialPlatformService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 客户联系人社交账号信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/platform")
public class CustomerContactsSocialPlatformController extends BaseController
{
    @Autowired
    private ICustomerContactsSocialPlatformService customerContactsSocialPlatformService;

    /**
     * 查询客户联系人社交账号信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:platform:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerContactsSocialPlatform customerContactsSocialPlatform)
    {
        startPage();
        List<CustomerContactsSocialPlatform> list = customerContactsSocialPlatformService.selectCustomerContactsSocialPlatformList(customerContactsSocialPlatform);
        return getDataTable(list);
    }

    /**
     * 导出客户联系人社交账号信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:platform:export')")
    @Log(title = "客户联系人社交账号信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerContactsSocialPlatform customerContactsSocialPlatform)
    {
        List<CustomerContactsSocialPlatform> list = customerContactsSocialPlatformService.selectCustomerContactsSocialPlatformList(customerContactsSocialPlatform);
        ExcelUtil<CustomerContactsSocialPlatform> util = new ExcelUtil<CustomerContactsSocialPlatform>(CustomerContactsSocialPlatform.class);
        return util.exportExcel(list, "客户联系人社交账号信息数据");
    }

    /**
     * 获取客户联系人社交账号信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:platform:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerContactsSocialPlatformService.selectCustomerContactsSocialPlatformById(id));
    }

    /**
     * 新增客户联系人社交账号信息
     */
    @PreAuthorize("@ss.hasPermi('system:platform:add')")
    @Log(title = "客户联系人社交账号信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerContactsSocialPlatform customerContactsSocialPlatform)
    {
        return toAjax(customerContactsSocialPlatformService.insertCustomerContactsSocialPlatform(customerContactsSocialPlatform));
    }

    /**
     * 修改客户联系人社交账号信息
     */
    @PreAuthorize("@ss.hasPermi('system:platform:edit')")
    @Log(title = "客户联系人社交账号信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerContactsSocialPlatform customerContactsSocialPlatform)
    {
        return toAjax(customerContactsSocialPlatformService.updateCustomerContactsSocialPlatform(customerContactsSocialPlatform));
    }

    /**
     * 删除客户联系人社交账号信息
     */
    @PreAuthorize("@ss.hasPermi('system:platform:remove')")
    @Log(title = "客户联系人社交账号信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerContactsSocialPlatformService.deleteCustomerContactsSocialPlatformByIds(ids));
    }
}
