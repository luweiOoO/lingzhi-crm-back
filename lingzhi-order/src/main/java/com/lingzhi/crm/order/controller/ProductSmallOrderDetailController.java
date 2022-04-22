package com.lingzhi.crm.order.controller;

import java.util.List;

import com.lingzhi.crm.common.annotation.Log;
import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.core.page.TableDataInfo;
import com.lingzhi.crm.common.enums.BusinessType;
import com.lingzhi.crm.common.utils.poi.ExcelUtil;
import com.lingzhi.crm.order.domin.ProductSmallOrderDetail;
import com.lingzhi.crm.order.servicce.IProductSmallOrderDetailService;
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
 * 生产指示单产品详情Controller
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@RestController
@RequestMapping("/orderDetail")
public class ProductSmallOrderDetailController extends BaseController
{
    @Autowired
    private IProductSmallOrderDetailService productSmallOrderDetailService;

    /**
     * 查询生产指示单产品详情列表
     */
    @PreAuthorize("@ss.hasPermi('order:orderDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductSmallOrderDetail productSmallOrderDetail)
    {
        startPage();
        List<ProductSmallOrderDetail> list = productSmallOrderDetailService.selectProductSmallOrderDetailList(productSmallOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出生产指示单产品详情列表
     */
    @PreAuthorize("@ss.hasPermi('order:orderDetail:export')")
    @Log(title = "生产指示单产品详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductSmallOrderDetail productSmallOrderDetail)
    {
        List<ProductSmallOrderDetail> list = productSmallOrderDetailService.selectProductSmallOrderDetailList(productSmallOrderDetail);
        ExcelUtil<ProductSmallOrderDetail> util = new ExcelUtil<ProductSmallOrderDetail>(ProductSmallOrderDetail.class);
        return util.exportExcel(list, "生产指示单产品详情数据");
    }

    /**
     * 获取生产指示单产品详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:orderDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(productSmallOrderDetailService.selectProductSmallOrderDetailById(id));
    }

    /**
     * 新增生产指示单产品详情
     */
    @PreAuthorize("@ss.hasPermi('order:orderDetail:add')")
    @Log(title = "生产指示单产品详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductSmallOrderDetail productSmallOrderDetail)
    {
        return toAjax(productSmallOrderDetailService.insertProductSmallOrderDetail(productSmallOrderDetail));
    }

    /**
     * 修改生产指示单产品详情
     */
    @PreAuthorize("@ss.hasPermi('order:orderDetail:edit')")
    @Log(title = "生产指示单产品详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductSmallOrderDetail productSmallOrderDetail)
    {
        return toAjax(productSmallOrderDetailService.updateProductSmallOrderDetail(productSmallOrderDetail));
    }

    /**
     * 删除生产指示单产品详情
     */
    @PreAuthorize("@ss.hasPermi('order:orderDetail:remove')")
    @Log(title = "生产指示单产品详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productSmallOrderDetailService.deleteProductSmallOrderDetailByIds(ids));
    }
}
