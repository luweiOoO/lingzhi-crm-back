package com.lingzhi.crm.order.domin;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 运单信息对象 product_waybill_message
 * 
 * @author ruoyi
 * @date 2022-02-10
 */
@Data
public class ProductWaybillMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 运单号 */
    @Excel(name = "运单号")
    private String waybillNo;

    /** 付款账号 */
    @Excel(name = "付款账号")
    private String payAccount;

    /** 发件日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发件日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 发件人 */
    @Excel(name = "发件人")
    private String deliveryName;

    /** 收件人 */
    @Excel(name = "收件人")
    private String receiveName;

    /** 收件公司 */
    @Excel(name = "收件公司")
    private String receiveCompany;

    /** 收件地址1 */
    @Excel(name = "收件地址1")
    private String receiveAddressA;

    /** 收件地址2 */
    @Excel(name = "收件地址2")
    private String receiveAddressB;

    /** 收件地址邮编 */
    @Excel(name = "收件地址邮编")
    private String receiveAddressPostalCode;

    /** 账单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "账单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentDate;

    /** 货物件数 */
    @Excel(name = "货物件数")
    private Integer goodsNumber;

    /** 重量 */
    @Excel(name = "重量")
    private BigDecimal goodsWeight;

    /** 货物价值 */
    @Excel(name = "货物价值")
    private BigDecimal goodsPrice;

    /** 运单金额 */
    @Excel(name = "运单金额")
    private BigDecimal waybillFare;

}
