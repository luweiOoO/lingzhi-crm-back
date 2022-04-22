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
import com.lingzhi.crm.customer.domain.CustomerGroup;
import com.lingzhi.crm.customer.service.ICustomerGroupService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 客户分组信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/group")
public class CustomerGroupController extends BaseController
{
    @Autowired
    private ICustomerGroupService customerGroupService;

    /**
     * 查询客户分组信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerGroup customerGroup)
    {
        startPage();
        List<CustomerGroup> list = customerGroupService.selectCustomerGroupList(customerGroup);
        return getDataTable(list);
    }

    /**
     * 导出客户分组信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:group:export')")
    @Log(title = "客户分组信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerGroup customerGroup)
    {
        List<CustomerGroup> list = customerGroupService.selectCustomerGroupList(customerGroup);
        ExcelUtil<CustomerGroup> util = new ExcelUtil<CustomerGroup>(CustomerGroup.class);
        return util.exportExcel(list, "客户分组信息数据");
    }

    /**
     * 获取客户分组信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerGroupService.selectCustomerGroupById(id));
    }

    /**
     * 新增客户分组信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:add')")
    @Log(title = "客户分组信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerGroup customerGroup)
    {
        return toAjax(customerGroupService.insertCustomerGroup(customerGroup));
    }

    /**
     * 修改客户分组信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:edit')")
    @Log(title = "客户分组信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerGroup customerGroup)
    {
        return toAjax(customerGroupService.updateCustomerGroup(customerGroup));
    }

    /**
     * 删除客户分组信息
     */
    @PreAuthorize("@ss.hasPermi('system:group:remove')")
    @Log(title = "客户分组信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerGroupService.deleteCustomerGroupByIds(ids));
    }

    /**
     * 查询客户分组信息列表（选择时使用）
     */
    @GetMapping("/selectGroupList")
    public TableDataInfo selectGroupList()
    {
        CustomerGroup customerGroup = new CustomerGroup();
        List<CustomerGroup> list = customerGroupService.selectCustomerGroupList(customerGroup);
        return getDataTable(list);
    }
}
