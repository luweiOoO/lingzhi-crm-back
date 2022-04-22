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
import com.lingzhi.crm.customer.domain.CustomerCategory;
import com.lingzhi.crm.customer.service.ICustomerCategoryService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 客户类别Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/category")
public class CustomerCategoryController extends BaseController
{
    @Autowired
    private ICustomerCategoryService customerCategoryService;

    /**
     * 查询客户类别列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerCategory customerCategory)
    {
        startPage();
        List<CustomerCategory> list = customerCategoryService.selectCustomerCategoryList(customerCategory);
        return getDataTable(list);
    }

    /**
     * 导出客户类别列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "客户类别", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerCategory customerCategory)
    {
        List<CustomerCategory> list = customerCategoryService.selectCustomerCategoryList(customerCategory);
        ExcelUtil<CustomerCategory> util = new ExcelUtil<CustomerCategory>(CustomerCategory.class);
        return util.exportExcel(list, "客户类别数据");
    }

    /**
     * 获取客户类别详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerCategoryService.selectCustomerCategoryById(id));
    }

    /**
     * 新增客户类别
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "客户类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerCategory customerCategory)
    {
        return toAjax(customerCategoryService.insertCustomerCategory(customerCategory));
    }

    /**
     * 修改客户类别
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "客户类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerCategory customerCategory)
    {
        return toAjax(customerCategoryService.updateCustomerCategory(customerCategory));
    }

    /**
     * 删除客户类别
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "客户类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerCategoryService.deleteCustomerCategoryByIds(ids));
    }

    /**
     * 查询客户类别列表(下拉框使用)
     */
    @GetMapping("/selectCategoryList")
    public TableDataInfo selectCategoryList()
    {
        CustomerCategory customerCategory = new CustomerCategory();
        List<CustomerCategory> list = customerCategoryService.selectCustomerCategoryList(customerCategory);
        return getDataTable(list);
    }
}
