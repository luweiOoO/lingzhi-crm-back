package com.lingzhi.crm.order.domin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 生产指示小单基础信息对象 product_small_order
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Data
public class ProductSmallOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 下单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 计划发货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划发货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planDeliveryDate;

    /** 客户国别 */
    @Excel(name = "客户国别")
    private String customerCountry;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 到账金额百分比 */
    @Excel(name = "到账金额百分比")
    private BigDecimal arrivalMoneyPercent;

    /** 货值总价(外币) */
    @Excel(name = "货值总价(外币)")
    private BigDecimal goodsAmountPriceDollar;

    /** 货值总价(人民币) */
    @Excel(name = "货值总价(人民币)")
    private BigDecimal goodsAmountPriceRmb;

    /** 美元兑人民币实时汇率 */
    @Excel(name = "美元兑人民币实时汇率")
    private BigDecimal dollarRmbExchangeRate;

    /** 付款渠道 */
    @Excel(name = "付款渠道")
    private String paymentChannel;

    /** 付款手续费 */
    @Excel(name = "付款手续费")
    private BigDecimal paymentServiceCharge;

    /** 阿里巴巴订单号 */
    @Excel(name = "阿里巴巴订单号")
    private String alibabaOrderNo;

    /** 快递类别 */
    @Excel(name = "快递类别")
    private String expressCategory;

    /** 订单质量要求 */
    @Excel(name = "订单质量要求")
    private String orderQualityRequire;

    /** 订单包装要求 */
    @Excel(name = "订单包装要求")
    private String orderPackageRequire;

    /** 订单发货日期要求 */
    @Excel(name = "订单发货日期要求")
    private String orderDeliveryDateRequire;

    /** 订单所属业务员id */
    @Excel(name = "订单所属业务员id")
    private Long orderSalesId;

    /** 订单所属业务员姓名 */
    @Excel(name = "订单所属业务员姓名")
    private String orderSalesName;

    /** 订单运单号(多个用逗号隔开) */
    @Excel(name = "订单运单号(多个用逗号隔开)")
    private String waybillNos;

    /** 订单运费 */
    private BigDecimal waybillFare;

    /** 订单产品详情列表 (非数据库字段) */
    private List<ProductSmallOrderDetail> orderDetailList;
}
