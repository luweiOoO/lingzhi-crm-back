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
import com.lingzhi.crm.customer.domain.CustomerStatus;
import com.lingzhi.crm.customer.service.ICustomerStatusService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 客户状态信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/status")
public class CustomerStatusController extends BaseController
{
    @Autowired
    private ICustomerStatusService customerStatusService;

    /**
     * 查询客户状态信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:status:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerStatus customerStatus)
    {
        startPage();
        List<CustomerStatus> list = customerStatusService.selectCustomerStatusList(customerStatus);
        return getDataTable(list);
    }

    /**
     * 导出客户状态信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:status:export')")
    @Log(title = "客户状态信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerStatus customerStatus)
    {
        List<CustomerStatus> list = customerStatusService.selectCustomerStatusList(customerStatus);
        ExcelUtil<CustomerStatus> util = new ExcelUtil<CustomerStatus>(CustomerStatus.class);
        return util.exportExcel(list, "客户状态信息数据");
    }

    /**
     * 获取客户状态信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:status:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerStatusService.selectCustomerStatusById(id));
    }

    /**
     * 新增客户状态信息
     */
    @PreAuthorize("@ss.hasPermi('system:status:add')")
    @Log(title = "客户状态信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerStatus customerStatus)
    {
        return toAjax(customerStatusService.insertCustomerStatus(customerStatus));
    }

    /**
     * 修改客户状态信息
     */
    @PreAuthorize("@ss.hasPermi('system:status:edit')")
    @Log(title = "客户状态信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerStatus customerStatus)
    {
        return toAjax(customerStatusService.updateCustomerStatus(customerStatus));
    }

    /**
     * 删除客户状态信息
     */
    @PreAuthorize("@ss.hasPermi('system:status:remove')")
    @Log(title = "客户状态信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerStatusService.deleteCustomerStatusByIds(ids));
    }

    /**
     * 查询客户状态信息列表（下拉框选择时调用）
     */
    @GetMapping("/selectStatusList")
    public TableDataInfo selectStatusList()
    {
        CustomerStatus customerStatus = new CustomerStatus();
        List<CustomerStatus> list = customerStatusService.selectCustomerStatusList(customerStatus);
        return getDataTable(list);
    }
}
