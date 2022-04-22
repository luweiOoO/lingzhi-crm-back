package com.lingzhi.crm.order.domin;

import java.math.BigDecimal;

import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 生产指示单产品详情对象 product_small_order_detail
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Data
public class ProductSmallOrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 所属订单id */
//    @Excel(name = "所属订单id")
    private Long orderId;

    /** 所属订单编码 */
//    @Excel(name = "所属订单编码")
    private String orderNo;

    /** 产品名称 */
    @Excel(name = "品名")
    private String productName;

    /** 产品完成长度要求 */
    @Excel(name = "完成长度")
    private String productLength;

    /** 产品颜色要求 */
    @Excel(name = "颜色（新金牌色环）")
    private String productColour;

    /** 产品造型要求 */
    @Excel(name = "造型")
    private String procuctModel;

    /** 产品规格要求 */
    @Excel(name = "规格")
    private String productStandard;

    /** 产品数量 */
    @Excel(name = "数量")
    private BigDecimal productNum;

    /** 产品重量 */
    @Excel(name = "重量")
    private BigDecimal productWeight;

    /** 出厂单价(人民币) */
    @Excel(name = "出厂单价")
    private BigDecimal productCostUnitPriceRmb;

    /** 贸易单价(外币) */
    @Excel(name = "贸易单价（外币）")
    private BigDecimal productSellUnitPriceDollar;

    /** 贸易总价(外币) */
    @Excel(name = "贸易价格（外币）")
    private BigDecimal productSellAmountDollar;
}
