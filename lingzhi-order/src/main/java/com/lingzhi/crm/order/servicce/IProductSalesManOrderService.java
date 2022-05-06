package com.lingzhi.crm.order.servicce;

import com.lingzhi.crm.common.domain.ProductSalesManOrderImportEntity;
import com.lingzhi.crm.order.dto.SalesmanOrderSearchParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 业务员订单信息相关接口
 */
public interface IProductSalesManOrderService {

    /**
     * 导入业务员订单信息
     * @param file
     * @return
     */
    int importSalesManOrder(MultipartFile file);

    /**
     * 查询业务员订单信息列表
     * @param param
     * @return
     */
    public List<ProductSalesManOrderImportEntity> selectSalesManOrderList(SalesmanOrderSearchParam param);
}
