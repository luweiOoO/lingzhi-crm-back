package com.lingzhi.crm.order.controller;

import java.util.List;

import com.lingzhi.crm.common.annotation.Log;
import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.core.page.TableDataInfo;
import com.lingzhi.crm.common.enums.BusinessType;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.order.domin.ProductSmallOrder;
import com.lingzhi.crm.order.dto.SmallOrderSearchParamsDto;
import com.lingzhi.crm.order.servicce.IProductSmallOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 生产指示小单基础信息Controller
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@RestController
@RequestMapping("/order")
public class ProductSmallOrderController extends BaseController
{
    @Autowired
    private IProductSmallOrderService productSmallOrderService;

    /**
     * 查询生产指示小单基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(SmallOrderSearchParamsDto smallOrderSearchParamsDto)
    {
        startPage();
        List<ProductSmallOrder> list = productSmallOrderService.selectProductSmallOrderList(smallOrderSearchParamsDto);
        return getDataTable(list);
    }

    /**
     * 导出生产指示小单基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:export')")
    @Log(title = "生产指示小单基础信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SmallOrderSearchParamsDto smallOrderSearchParamsDto)
    {
        List<ProductSmallOrder> list = productSmallOrderService.selectProductSmallOrderList(smallOrderSearchParamsDto);
        ExcelUtil<ProductSmallOrder> util = new ExcelUtil<ProductSmallOrder>(ProductSmallOrder.class);
        return util.exportExcel(list, "生产指示小单基础信息数据");
    }

    /**
     * 获取生产指示小单基础信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(productSmallOrderService.selectProductSmallOrderById(id));
    }

    /**
     * 新增生产指示小单基础信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:add')")
    @Log(title = "生产指示小单基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductSmallOrder productSmallOrder)
    {
        return toAjax(productSmallOrderService.insertProductSmallOrder(productSmallOrder));
    }

    /**
     * 修改生产指示小单基础信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:edit')")
    @Log(title = "生产指示小单基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductSmallOrder productSmallOrder)
    {
        return toAjax(productSmallOrderService.updateProductSmallOrder(productSmallOrder));
    }

    /**
     * 删除生产指示小单基础信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:remove')")
    @Log(title = "生产指示小单基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productSmallOrderService.deleteProductSmallOrderByIds(ids));
    }

    /**
     * 生产指示单导入
     * @param file
     * @return
     */
    @PostMapping("importOrder")
    public AjaxResult importOrder(@RequestPart("file") MultipartFile file){
        try {
            productSmallOrderService.importProductSmallOrder(file);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 生产指示单绑定运单号
     * @param productSmallOrder waybillNos为输入的运单号(多个时用逗号隔开)
     * @return
     */
    @PostMapping("bindWaybill")
    public AjaxResult bindWaybill(@RequestBody ProductSmallOrder productSmallOrder){
        try {
            productSmallOrderService.bindWaybill(productSmallOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }
}
