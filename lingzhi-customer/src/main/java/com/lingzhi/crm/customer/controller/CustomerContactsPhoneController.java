package com.lingzhi.crm.customer.controller;

import java.util.List;

import com.lingzhi.crm.common.annotation.Log;
import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.core.page.TableDataInfo;
import com.lingzhi.crm.common.enums.BusinessType;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.customer.domain.CustomerContactsPhone;
import com.lingzhi.crm.customer.service.ICustomerContactsPhoneService;
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

/**
 * 客户联系人联系电话信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/phone")
public class CustomerContactsPhoneController extends BaseController
{
    @Autowired
    private ICustomerContactsPhoneService customerContactsPhoneService;

    /**
     * 查询客户联系人联系电话信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:phone:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerContactsPhone customerContactsPhone)
    {
        startPage();
        List<CustomerContactsPhone> list = customerContactsPhoneService.selectCustomerContactsPhoneList(customerContactsPhone);
        return getDataTable(list);
    }

    /**
     * 导出客户联系人联系电话信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:phone:export')")
    @Log(title = "客户联系人联系电话信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerContactsPhone customerContactsPhone)
    {
        List<CustomerContactsPhone> list = customerContactsPhoneService.selectCustomerContactsPhoneList(customerContactsPhone);
        ExcelUtil<CustomerContactsPhone> util = new ExcelUtil<CustomerContactsPhone>(CustomerContactsPhone.class);
        return util.exportExcel(list, "客户联系人联系电话信息数据");
    }

    /**
     * 获取客户联系人联系电话信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:phone:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerContactsPhoneService.selectCustomerContactsPhoneById(id));
    }

    /**
     * 新增客户联系人联系电话信息
     */
    @PreAuthorize("@ss.hasPermi('system:phone:add')")
    @Log(title = "客户联系人联系电话信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerContactsPhone customerContactsPhone)
    {
        return toAjax(customerContactsPhoneService.insertCustomerContactsPhone(customerContactsPhone));
    }

    /**
     * 修改客户联系人联系电话信息
     */
    @PreAuthorize("@ss.hasPermi('system:phone:edit')")
    @Log(title = "客户联系人联系电话信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerContactsPhone customerContactsPhone)
    {
        return toAjax(customerContactsPhoneService.updateCustomerContactsPhone(customerContactsPhone));
    }

    /**
     * 删除客户联系人联系电话信息
     */
    @PreAuthorize("@ss.hasPermi('system:phone:remove')")
    @Log(title = "客户联系人联系电话信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerContactsPhoneService.deleteCustomerContactsPhoneByIds(ids));
    }
}
