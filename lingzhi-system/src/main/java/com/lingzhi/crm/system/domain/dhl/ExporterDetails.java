package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.util.List;

/**
 * 出口商信息节点
 */
@Data
public class ExporterDetails {

    /**
     * 出口商类别
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
     * 出口商联系人节点
     */
    private ContactInformation contactInformation;

    /**
     * 出口商地址节点
     */
    private PostalAddress postalAddress;

    /**
     * 出口商注册号/税号节点
     */
    private List<RegistrationNumber> registrationNumbers;

    /**
     * 出口商银行信息节点
     */
    private List<BankDetail> bankDetails;
}