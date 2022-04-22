package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.util.List;

/**
 * 收件人信息节点
 */
@Data
public class ReceiverDetails {


    /**
     * 收件人类别
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
     * 收件人联系方式信息节点
     */
    private ContactInformation contactInformation;

    /**
     * 收件人地址信息节点
     */
    private PostalAddress postalAddress;

    /**
     * 收件人注册号/税号节点
     */
    private List<RegistrationNumber> registrationNumbers;

    /**
     * 收件人银行信息节点（仅适用于俄国）
     */
    private List<BankDetail> bankDetails;



}
