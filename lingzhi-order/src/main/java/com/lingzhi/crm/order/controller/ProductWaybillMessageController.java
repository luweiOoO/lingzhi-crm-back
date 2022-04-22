package com.lingzhi.crm.order.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.lingzhi.crm.common.annotation.DataScope;
import com.lingzhi.crm.common.annotation.FieldScope;
import com.lingzhi.crm.common.annotation.Log;
import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.core.page.TableDataInfo;
import com.lingzhi.crm.common.enums.BusinessType;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.order.domin.ProductWaybillMessage;
import com.lingzhi.crm.order.dto.WaybillMessageParamsDto;
import com.lingzhi.crm.order.servicce.IProductWaybillMessageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 运单信息Controller
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@RestController
@RequestMapping("/waybill")
public class ProductWaybillMessageController extends BaseController
{
    @Autowired
    private IProductWaybillMessageService productWaybillMessageService;

    /**
     * 查询运单信息列表
     */
    @PreAuthorize("@ss.hasPermi('order:waybill:list')")
    @DataScope(deptAlias = "product_waybill_message",userAlias = "product_waybill_message")
    @FieldScope
    @GetMapping("/list")
    public TableDataInfo list(WaybillMessageParamsDto waybillMessageParamsDto)
    {
        startPage();
        List<ProductWaybillMessage> list = productWaybillMessageService.selectProductWaybillMessageList(waybillMessageParamsDto);
        return getDataTable(list);
    }

    /**
     * 导出运单信息列表
     */
    @PreAuthorize("@ss.hasPermi('order:waybill:export')")
    @Log(title = "运单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WaybillMessageParamsDto waybillMessageParamsDto)
    {
        List<ProductWaybillMessage> list = productWaybillMessageService.selectProductWaybillMessageList(waybillMessageParamsDto);
        ExcelUtil<ProductWaybillMessage> util = new ExcelUtil<ProductWaybillMessage>(ProductWaybillMessage.class);
        return util.exportExcel(list, "运单信息数据");
    }


    /**
     * 导入运单信息
     * @param file
     * @return
     */
    @PostMapping("/importWaybill")
    public AjaxResult importWaybill(@RequestPart("file") MultipartFile file){
        try {
            productWaybillMessageService.importWaybill(file);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 获取运单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:waybill:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(productWaybillMessageService.selectProductWaybillMessageById(id));
    }

    /**
     * 新增运单信息
     */
    @PreAuthorize("@ss.hasPermi('order:waybill:add')")
    @Log(title = "运单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductWaybillMessage productWaybillMessage)
    {
        return toAjax(productWaybillMessageService.insertProductWaybillMessage(productWaybillMessage));
    }

    /**
     * 修改运单信息
     */
    @PreAuthorize("@ss.hasPermi('order:waybill:edit')")
    @Log(title = "运单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductWaybillMessage productWaybillMessage)
    {
        return toAjax(productWaybillMessageService.updateProductWaybillMessage(productWaybillMessage));
    }

    /**
     * 删除运单信息
     */
    @PreAuthorize("@ss.hasPermi('order:waybill:remove')")
    @Log(title = "运单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productWaybillMessageService.deleteProductWaybillMessageByIds(ids));
    }
}
