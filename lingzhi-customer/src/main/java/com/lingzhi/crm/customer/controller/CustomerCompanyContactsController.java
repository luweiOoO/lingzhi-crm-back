package com.lingzhi.crm.customer.controller;

import java.util.List;

import com.lingzhi.crm.common.annotation.Log;
import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.core.page.TableDataInfo;
import com.lingzhi.crm.common.enums.BusinessType;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.customer.domain.CustomerCompanyContacts;
import com.lingzhi.crm.customer.service.ICustomerCompanyContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 客户公司联系人信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/contacts")
public class CustomerCompanyContactsController extends BaseController
{
    @Autowired
    private ICustomerCompanyContactsService customerCompanyContactsService;

    /**
     * 查询客户公司联系人信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:contacts:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerCompanyContacts customerCompanyContacts)
    {
        startPage();
        List<CustomerCompanyContacts> list = customerCompanyContactsService.selectCustomerCompanyContactsList(customerCompanyContacts);
        return getDataTable(list);
    }

    /**
     * 导出客户公司联系人信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:export')")
    @Log(title = "客户公司联系人信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerCompanyContacts customerCompanyContacts)
    {
        List<CustomerCompanyContacts> list = customerCompanyContactsService.selectCustomerCompanyContactsList(customerCompanyContacts);
        ExcelUtil<CustomerCompanyContacts> util = new ExcelUtil<CustomerCompanyContacts>(CustomerCompanyContacts.class);
        return util.exportExcel(list, "客户公司联系人信息数据");
    }

    /**
     * 获取客户公司联系人信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerCompanyContactsService.selectCustomerCompanyContactsById(id));
    }

    /**
     * 新增客户公司联系人信息
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:add')")
    @Log(title = "客户公司联系人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerCompanyContacts customerCompanyContacts)
    {
        return toAjax(customerCompanyContactsService.insertCustomerCompanyContacts(customerCompanyContacts));
    }

    /**
     * 修改客户公司联系人信息
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:edit')")
    @Log(title = "客户公司联系人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerCompanyContacts customerCompanyContacts)
    {
        return toAjax(customerCompanyContactsService.updateCustomerCompanyContacts(customerCompanyContacts));
    }

    /**
     * 删除客户公司联系人信息
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:remove')")
    @Log(title = "客户公司联系人信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> idList)
    {
        return toAjax(customerCompanyContactsService.deleteCustomerCompanyContactsByIds(idList));
    }
}
