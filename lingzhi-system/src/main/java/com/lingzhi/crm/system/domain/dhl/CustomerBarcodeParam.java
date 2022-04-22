package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 客户条码信息
 * 必须将label的templateName设置为‘ECOM26_84CI_002’
 */
@Data
public class CustomerBarcodeParam {

    /**
     * 条形码
     */
    private String content;

    /**
     * 条形码下方文字
     */
    private String textBelowBarcode;

    /**
     * 条形码类型
     * 可选值：93，39，128
     */
    private String symbologyCode;
}
