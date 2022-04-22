package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.util.List;

/**
 * 买家信息节点
 */
@Data
public class BuyerDetails {

    /**
     * 买家类别
     * "该字段的常用可选值为：
     *   - business
     *   - direct_consumer
     *   - government
     *   - other
     *   - private
     *   - reseller"
     */
    private String typeCode;

    /**
     * 联系人信息节点
     */
    private ContactInformation contactInformation;

    /**
     * 地址信息节点
     */
    private PostalAddress postalAddress;

    /**
     * 买家注册号/税号节点
     */
    private List<RegistrationNumber> registrationNumbers;

    /**
     * 买家银行信息节点（仅适用于俄国）
     */
    private List<BankDetail> bankDetails;
}
