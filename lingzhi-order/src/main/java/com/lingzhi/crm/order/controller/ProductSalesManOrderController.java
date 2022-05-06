package com.lingzhi.crm.order.controller;

import com.lingzhi.crm.common.core.controller.BaseController;
import com.lingzhi.crm.common.core.domain.AjaxResult;
import com.lingzhi.crm.common.core.page.TableDataInfo;
import com.lingzhi.crm.common.domain.ProductSalesManOrderImportEntity;
import com.lingzhi.crm.order.dto.SalesmanOrderSearchParam;
import com.lingzhi.crm.order.servicce.IProductSalesManOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 业务员订单信息相关接口
 */
@RestController
@RequestMapping("/salesman")
public class ProductSalesManOrderController extends BaseController {

    @Autowired
    private IProductSalesManOrderService productSalesManOrderService;

    /**
     * 业务员订单信息导入
     * @param file
     * @return
     */
    @PostMapping("/import")
    public AjaxResult importSalesManOrder(@RequestPart("file") MultipartFile file){
        productSalesManOrderService.importSalesManOrder(file);
        return AjaxResult.success();
    }

    /**
     * 查询业务员订单信息列表
     * @param param
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo getSalesOrderList(SalesmanOrderSearchParam param){
        startPage();
        List<ProductSalesManOrderImportEntity> list = productSalesManOrderService.selectSalesManOrderList(param);
        return getDataTable(list);
    }
}
