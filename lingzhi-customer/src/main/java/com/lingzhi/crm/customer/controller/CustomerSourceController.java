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
import com.lingzhi.crm.customer.domain.CustomerSource;
import com.lingzhi.crm.customer.service.ICustomerSourceService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 客户来源信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/source")
public class CustomerSourceController extends BaseController
{
    @Autowired
    private ICustomerSourceService customerSourceService;

    /**
     * 查询客户来源信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:source:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerSource customerSource)
    {
        startPage();
        List<CustomerSource> list = customerSourceService.selectCustomerSourceList(customerSource);
        return getDataTable(list);
    }

    /**
     * 导出客户来源信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:source:export')")
    @Log(title = "客户来源信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerSource customerSource)
    {
        List<CustomerSource> list = customerSourceService.selectCustomerSourceList(customerSource);
        ExcelUtil<CustomerSource> util = new ExcelUtil<CustomerSource>(CustomerSource.class);
        return util.exportExcel(list, "客户来源信息数据");
    }

    /**
     * 获取客户来源信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:source:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerSourceService.selectCustomerSourceById(id));
    }

    /**
     * 新增客户来源信息
     */
    @PreAuthorize("@ss.hasPermi('system:source:add')")
    @Log(title = "客户来源信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerSource customerSource)
    {
        return toAjax(customerSourceService.insertCustomerSource(customerSource));
    }

    /**
     * 修改客户来源信息
     */
    @PreAuthorize("@ss.hasPermi('system:source:edit')")
    @Log(title = "客户来源信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerSource customerSource)
    {
        return toAjax(customerSourceService.updateCustomerSource(customerSource));
    }

    /**
     * 删除客户来源信息
     */
    @PreAuthorize("@ss.hasPermi('system:source:remove')")
    @Log(title = "客户来源信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerSourceService.deleteCustomerSourceByIds(ids));
    }

    /**
     * 查询客户来源信息列表(选择器使用)
     */
    @GetMapping("/selectList")
    public TableDataInfo selectList()
    {
        CustomerSource customerSource = new CustomerSource();
        List<CustomerSource> list = customerSourceService.selectCustomerSourceList(customerSource);
        return getDataTable(list);
    }
}
