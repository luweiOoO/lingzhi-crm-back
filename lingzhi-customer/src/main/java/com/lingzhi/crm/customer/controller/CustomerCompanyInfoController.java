package com.lingzhi.crm.customer.controller;

import java.util.List;

import com.lingzhi.crm.common.annotation.Log;
import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.core.page.TableDataInfo;
import com.lingzhi.crm.common.enums.BusinessType;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.customer.domain.CustomerCompanyInfo;
import com.lingzhi.crm.customer.service.ICustomerCompanyInfoService;
import com.lingzhi.crm.customer.vo.CompanyCustomerAddInVO;
import com.lingzhi.crm.customer.vo.CompanyCustomerDetailOutVO;
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
 * 客户公司基础信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/customer/company/info")
public class CustomerCompanyInfoController extends BaseController
{
    @Autowired
    private ICustomerCompanyInfoService customerCompanyInfoService;

//    /**
//     * 查询客户公司基础信息列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:info:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(CustomerCompanyInfo customerCompanyInfo)
//    {
//        startPage();
//        List<CustomerCompanyInfo> list = customerCompanyInfoService.selectCustomerCompanyInfoList(customerCompanyInfo);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出客户公司基础信息列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:info:export')")
//    @Log(title = "客户公司基础信息", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(CustomerCompanyInfo customerCompanyInfo)
//    {
//        List<CustomerCompanyInfo> list = customerCompanyInfoService.selectCustomerCompanyInfoList(customerCompanyInfo);
//        ExcelUtil<CustomerCompanyInfo> util = new ExcelUtil<CustomerCompanyInfo>(CustomerCompanyInfo.class);
//        return util.exportExcel(list, "客户公司基础信息数据");
//    }
//
//    /**
//     * 获取客户公司基础信息详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:info:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        return AjaxResult.success(customerCompanyInfoService.selectCustomerCompanyInfoById(id));
//    }
//
//    /**
//     * 新增客户公司基础信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:info:add')")
//    @Log(title = "客户公司基础信息", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody CustomerCompanyInfo customerCompanyInfo)
//    {
//        return toAjax(customerCompanyInfoService.insertCustomerCompanyInfo(customerCompanyInfo));
//    }
//
//    /**
//     * 修改客户公司基础信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:info:edit')")
//    @Log(title = "客户公司基础信息", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody CustomerCompanyInfo customerCompanyInfo)
//    {
//        return toAjax(customerCompanyInfoService.updateCustomerCompanyInfo(customerCompanyInfo));
//    }
//
//    /**
//     * 删除客户公司基础信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:info:remove')")
//    @Log(title = "客户公司基础信息", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(customerCompanyInfoService.deleteCustomerCompanyInfoByIds(ids));
//    }

    /**
     * 查询客户公司基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('customer:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerCompanyInfo customerCompanyInfo)
    {
        startPage();
//        List<CustomerCompanyInfo> list = customerCompanyInfoService.selectCustomerCompanyInfoList(customerCompanyInfo);
        List<CompanyCustomerDetailOutVO> list = customerCompanyInfoService.selectCustomerCompanyInfoDetailList(customerCompanyInfo);
        return getDataTable(list);
    }

    /**
     * 新增客户公司基本信息
     * @param customerAddInVO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:company:add')")
    @Log(title = "客户公司基本信息新增", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompanyCustomerAddInVO customerAddInVO)
    {
        return toAjax(customerCompanyInfoService.addCompanyAndContacts(customerAddInVO));
    }

    /**
     * 查询客户详情
     * @param id 客户id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:company:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerCompanyInfoService.getCompanyDetailById(id));
    }

    /**
     * 修改客户信息
     */
    @PreAuthorize("@ss.hasPermi('customer:company:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody CompanyCustomerDetailOutVO params){

        return AjaxResult.success(customerCompanyInfoService.editCompanyDetail(params));
    }

     /**
     * 删除客户公司基础信息
     */
    @PreAuthorize("@ss.hasPermi('customer:company:remove')")
    @Log(title = "客户公司基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerCompanyInfoService.deleteCustomerCompanyInfoByIds(ids));
    }

}
