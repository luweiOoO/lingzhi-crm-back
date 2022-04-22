package com.lingzhi.crm.mail.controller;

import java.util.List;

import com.lingzhi.crm.mail.vo.EmailAccountBindChangeInVO;
import com.lingzhi.crm.mail.vo.EmailAccountListOutVO;
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
import com.lingzhi.crm.mail.domain.EmailAccountInfo;
import com.lingzhi.crm.mail.service.IEmailAccountInfoService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 邮箱账户信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-16
 */
@RestController
@RequestMapping("/email/info")
public class EmailAccountInfoController extends BaseController
{
    @Autowired
    private IEmailAccountInfoService emailAccountInfoService;

    /**
     * 查询邮箱账户信息列表
     */
    @PreAuthorize("@ss.hasPermi('email:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(EmailAccountInfo emailAccountInfo)
    {
        startPage();
        List<EmailAccountListOutVO> list = emailAccountInfoService.selectEmailAccountInfoList(emailAccountInfo);
        return getDataTable(list);
    }

//    /**
//     * 导出邮箱账户信息列表
//     */
//    @PreAuthorize("@ss.hasPermi('email:info:export')")
//    @Log(title = "邮箱账户信息", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(EmailAccountInfo emailAccountInfo)
//    {
//        List<EmailAccountInfo> list = emailAccountInfoService.selectEmailAccountInfoList(emailAccountInfo);
//        ExcelUtil<EmailAccountInfo> util = new ExcelUtil<EmailAccountInfo>(EmailAccountInfo.class);
//        return util.exportExcel(list, "邮箱账户信息数据");
//    }

    /**
     * 获取邮箱账户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('email:info:query')")
    @GetMapping(value = "/{emailId}")
    public AjaxResult getInfo(@PathVariable("emailId") Long emailId)
    {
        return AjaxResult.success(emailAccountInfoService.selectEmailAccountInfoById(emailId));
    }

    /**
     * 新增邮箱账户信息
     */
    @PreAuthorize("@ss.hasPermi('email:info:add')")
    @Log(title = "邮箱账户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmailAccountInfo emailAccountInfo)
    {
        return toAjax(emailAccountInfoService.insertEmailAccountInfo(emailAccountInfo));
    }

    /**
     * 修改邮箱账户信息
     */
    @PreAuthorize("@ss.hasPermi('email:info:edit')")
    @Log(title = "邮箱账户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmailAccountInfo emailAccountInfo)
    {
        return toAjax(emailAccountInfoService.updateEmailAccountInfo(emailAccountInfo));
    }

    /**
     * 删除邮箱账户信息
     */
    @PreAuthorize("@ss.hasPermi('email:info:remove')")
    @Log(title = "邮箱账户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emailIds}")
    public AjaxResult remove(@PathVariable Long[] emailIds)
    {
        return toAjax(emailAccountInfoService.deleteEmailAccountInfoByIds(emailIds));
    }

    /**
     * 邮箱绑定信息修改
     * @param params
     * @return
     */
//    @PreAuthorize("@ss.hasPermin('email:bindchange:remove')")
    @Log(title = "邮箱账户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/bindChange")
    public AjaxResult bindChange(@RequestBody EmailAccountBindChangeInVO params){

        return toAjax(emailAccountInfoService.emailAccountBindChange(params));
    }

    /**
     * 检测邮箱账户信息
     * @param emailId 邮箱id
     * @return
     */
    @GetMapping(value = "/testEmail/{emailId}")
    public AjaxResult testEmail(@PathVariable("emailId") Long emailId){
        return AjaxResult.success(emailAccountInfoService.emailTestConnect(emailId));
    }
}
