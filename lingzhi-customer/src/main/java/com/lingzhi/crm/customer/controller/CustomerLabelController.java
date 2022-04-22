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
import com.lingzhi.crm.customer.domain.CustomerLabel;
import com.lingzhi.crm.customer.service.ICustomerLabelService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 客户标签Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/label")
public class CustomerLabelController extends BaseController
{
    @Autowired
    private ICustomerLabelService customerLabelService;

    /**
     * 查询客户标签列表
     */
    @PreAuthorize("@ss.hasPermi('system:label:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerLabel customerLabel)
    {
        startPage();
        List<CustomerLabel> list = customerLabelService.selectCustomerLabelList(customerLabel);
        return getDataTable(list);
    }

    /**
     * 导出客户标签列表
     */
    @PreAuthorize("@ss.hasPermi('system:label:export')")
    @Log(title = "客户标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerLabel customerLabel)
    {
        List<CustomerLabel> list = customerLabelService.selectCustomerLabelList(customerLabel);
        ExcelUtil<CustomerLabel> util = new ExcelUtil<CustomerLabel>(CustomerLabel.class);
        return util.exportExcel(list, "客户标签数据");
    }

    /**
     * 获取客户标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:label:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerLabelService.selectCustomerLabelById(id));
    }

    /**
     * 新增客户标签
     */
    @PreAuthorize("@ss.hasPermi('system:label:add')")
    @Log(title = "客户标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerLabel customerLabel)
    {
        return toAjax(customerLabelService.insertCustomerLabel(customerLabel));
    }

    /**
     * 修改客户标签
     */
    @PreAuthorize("@ss.hasPermi('system:label:edit')")
    @Log(title = "客户标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerLabel customerLabel)
    {
        return toAjax(customerLabelService.updateCustomerLabel(customerLabel));
    }

    /**
     * 删除客户标签
     */
    @PreAuthorize("@ss.hasPermi('system:label:remove')")
    @Log(title = "客户标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerLabelService.deleteCustomerLabelByIds(ids));
    }

    /**
     * 查询客户标签列表(下拉框选择时调用)
     */
    @GetMapping("/selectLabelList")
    public TableDataInfo selectLabelList()
    {
        CustomerLabel customerLabel = new CustomerLabel();
        List<CustomerLabel> list = customerLabelService.selectCustomerLabelList(customerLabel);
        return getDataTable(list);
    }
}
