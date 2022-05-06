package com.lingzhi.crm.order.dto;

import lombok.Data;

/**
 * 查询业务员订单列表请求参数
 */
@Data
public class SalesmanOrderSearchParam {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 业务员名称
     */
    private String orderSalesName;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 运单号
     */
    private String waybillNo;
}
