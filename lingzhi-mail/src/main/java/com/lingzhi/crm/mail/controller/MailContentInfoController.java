package com.lingzhi.crm.mail.controller;

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
import com.lingzhi.crm.mail.domain.MailContentInfo;
import com.lingzhi.crm.mail.service.IMailContentInfoService;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.common.core.page.TableDataInfo;

/**
 * 邮件基础信息Controller
 * 
 * @author ruoyi
 * @date 2021-06-15
 */
@RestController
@RequestMapping("/mail/info")
public class MailContentInfoController extends BaseController
{
    @Autowired
    private IMailContentInfoService mailContentInfoService;

    /**
     * 查询邮件基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('mail:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(MailContentInfo mailContentInfo)
    {
        startPage();
        List<MailContentInfo> list = mailContentInfoService.selectMailContentInfoList(mailContentInfo);
        return getDataTable(list);
    }

    /**
     * 导出邮件基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('mail:info:export')")
    @Log(title = "邮件基础信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MailContentInfo mailContentInfo)
    {
        List<MailContentInfo> list = mailContentInfoService.selectMailContentInfoList(mailContentInfo);
        ExcelUtil<MailContentInfo> util = new ExcelUtil<MailContentInfo>(MailContentInfo.class);
        return util.exportExcel(list, "邮件基础信息数据");
    }

    /**
     * 获取邮件基础信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mail:info:query')")
    @GetMapping(value = "/{mailId}")
    public AjaxResult getInfo(@PathVariable("mailId") Long mailId)
    {
        return AjaxResult.success(mailContentInfoService.selectMailContentInfoById(mailId));
    }

    /**
     * 新增邮件基础信息
     */
    @PreAuthorize("@ss.hasPermi('mail:info:add')")
    @Log(title = "邮件基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MailContentInfo mailContentInfo)
    {
        return toAjax(mailContentInfoService.insertMailContentInfo(mailContentInfo));
    }

    /**
     * 修改邮件基础信息
     */
    @PreAuthorize("@ss.hasPermi('mail:info:edit')")
    @Log(title = "邮件基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MailContentInfo mailContentInfo)
    {
        return toAjax(mailContentInfoService.updateMailContentInfo(mailContentInfo));
    }

    /**
     * 删除邮件基础信息
     */
    @PreAuthorize("@ss.hasPermi('mail:info:remove')")
    @Log(title = "邮件基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mailIds}")
    public AjaxResult remove(@PathVariable Long[] mailIds)
    {
        return toAjax(mailContentInfoService.deleteMailContentInfoByIds(mailIds));
    }

    /**
     * 接收所有账户邮件并更新数据库
     * @return
     */
    @PostMapping("/receive")
    public AjaxResult receiveMail(){
        return toAjax(mailContentInfoService.receiveMail());
    }

}
