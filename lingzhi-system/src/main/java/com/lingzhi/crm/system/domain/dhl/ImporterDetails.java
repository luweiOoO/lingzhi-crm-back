package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.util.List;

/**
 * 进口商信息节点
 * 只有存在除了收、发件人以外的第三方进口商时，才需要考虑添加该字段。该字段的信息不显示在运单上，只显示在通过接口创建的清关发票上
 */
@Data
public class ImporterDetails {

    /**
     * 进口商类别
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
     * 进口商联系人信息节点
     */
    private ContactInformation contactInformation;

    /**
     * 进口商地址节点
     */
    private PostalAddress postalAddress;

    /**
     * 进口商注册号/税号节点
     */
    private List<RegistrationNumber> registrationNumbers;

    /**
     * 进口商银行信息节点（仅适用于俄国）
     */
    private List<BankDetail> bankDetails;


}
