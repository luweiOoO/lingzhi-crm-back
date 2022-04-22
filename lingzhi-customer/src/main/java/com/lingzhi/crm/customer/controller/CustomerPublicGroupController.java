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
import com.lingzhi.crm.customer.domain.CustomerPublicGroup;
import com.lingzhi.crm.customer.service.ICustomerPublicGroupService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 客户公海分组信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/publicGroup")
public class CustomerPublicGroupController extends BaseController
{
    @Autowired
    private ICustomerPublicGroupService customerPublicGroupService;

    /**
     * 查询客户公海分组信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerPublicGroup customerPublicGroup)
    {
        startPage();
        List<CustomerPublicGroup> list = customerPublicGroupService.selectCustomerPublicGroupList(customerPublicGroup);
        return getDataTable(list);
    }

    /**
     * 导出客户公海分组信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:export')")
    @Log(title = "客户公海分组信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerPublicGroup customerPublicGroup)
    {
        List<CustomerPublicGroup> list = customerPublicGroupService.selectCustomerPublicGroupList(customerPublicGroup);
        ExcelUtil<CustomerPublicGroup> util = new ExcelUtil<CustomerPublicGroup>(CustomerPublicGroup.class);
        return util.exportExcel(list, "客户公海分组信息数据");
    }

    /**
     * 获取客户公海分组信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerPublicGroupService.selectCustomerPublicGroupById(id));
    }

    /**
     * 新增客户公海分组信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:add')")
    @Log(title = "客户公海分组信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerPublicGroup customerPublicGroup)
    {
        return toAjax(customerPublicGroupService.insertCustomerPublicGroup(customerPublicGroup));
    }

    /**
     * 修改客户公海分组信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:edit')")
    @Log(title = "客户公海分组信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerPublicGroup customerPublicGroup)
    {
        return toAjax(customerPublicGroupService.updateCustomerPublicGroup(customerPublicGroup));
    }

    /**
     * 删除客户公海分组信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:remove')")
    @Log(title = "客户公海分组信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerPublicGroupService.deleteCustomerPublicGroupByIds(ids));
    }

    /**
     * 查询客户公海分组信息列表(下拉框选择时调用)
     */
    @GetMapping("/selectPublicGroupList")
    public TableDataInfo selectPublicGroupList()
    {
        CustomerPublicGroup customerPublicGroup = new CustomerPublicGroup();
        List<CustomerPublicGroup> list = customerPublicGroupService.selectCustomerPublicGroupList(customerPublicGroup);
        return getDataTable(list);
    }
}
