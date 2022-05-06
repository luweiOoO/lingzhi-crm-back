package com.lingzhi.crm.common.domain;

import com.lingzhi.crm.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 业务订单信息excel导入接收实体类
 */
@Data
public class ProductSalesManOrderImportEntity {

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String salesmanName;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String orderStatus;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderNo;

    /**
     * 到账日期
     */
    @Excel(name = "到账日期")
    private String paymentDate;

    /**
     * 付款方式
     */
    @Excel(name = "付款方式")
    private String paymentChannel;

    /**
     * 金额USD
     */
    @Excel(name = "金额USD")
    private BigDecimal goodsAmountPriceDollar;

    /**
     * 收款情况
     */
    @Excel(name = "收款情况")
    private String arrivalMoneyPercent;

    /**
     * 客户公司名称
     */
    @Excel(name = "客户公司名称")
    private String customerCompanyName;

    /**
     * 客户名
     */
    @Excel(name = "客户名")
    private String customerName;

    /**
     * 客户代号
     */
    @Excel(name = "客户代号")
    private String customerNickName;

    /**
     * 客户国家
     */
    @Excel(name = "客户国家")
    private String customerCountry;

    /**
     * 客户电话
     */
    @Excel(name = "客户电话")
    private String customerPhone;

    /**
     * 客户地址
     */
    @Excel(name = "客户地址")
    private String customerAddress;

    /**
     * 客户来源
     */
    @Excel(name = "客户来源")
    private String customerSource;

    /**
     * 快递类别
     */
    @Excel(name = "快递类别")
    private String expressCategory;

    /**
     * 快递单号
     */
    @Excel(name = "快递单号")
    private String waybillNos;

    /**
     * 快递费
     */
    @Excel(name = "快递费")
    private BigDecimal waybillFare;

    /**
     * 代理出口合同号
     */
    @Excel(name = "代理出口合同号")
    private String agentContractNo;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
}
