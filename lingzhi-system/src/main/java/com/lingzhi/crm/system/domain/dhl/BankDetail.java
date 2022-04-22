package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 发件人银行信息节点（仅适用于俄国）
 */
@Data
public class BankDetail {

    /**
     * 银行名称（仅适用于俄国）
     * 对应清关发票上的Russia Bank Name
     */
    private String name;

    /**
     * 银行本币结算账号（仅适用于俄国）
     * 对应清关发票上的Russia Bank Settlement Account Number in RUR
     */
    private String settlementLocalCurrency;

    /**
     * 银行外币结算账号（仅适用于俄国）
     * 对应清关发票上的Russia Bank Settlement Account Number in USD/EUR
     */
    private String settlementForeignCurrency;
}
