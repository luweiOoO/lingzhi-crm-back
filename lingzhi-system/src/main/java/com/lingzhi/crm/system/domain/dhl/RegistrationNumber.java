package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 发件人注册号/税号节点
 */
@Data
public class RegistrationNumber {

    /**
     * 发件人注册号/税号
     */
    private String number;

    /**
     * 发件人注册号/税号类别
     * "常用可选值为:（逗号左边为可选值，逗号右边为可选值的含义）
     * - VAT, VAT Registration (VAT / GST)
     * - EOR, Economic Operator Registration ID
     * - EIN, Employer Identification Number
     * - SDT, 该可选值基于不同的国家有不同的含义
     *
     * 对于发往欧盟的包裹类快件，如果客户考虑使用发件人在欧盟注册的VAT号码（IOSS-Import One Stop Shop ）进行目的地清关并支
     * 付目的地增值税，则需要将该字段的值设置为SDT，相应的
     * issuerCountryCode设置为IOSS号的所属国（即在哪个国家申请的IOSS号），并在相应的number字段输入IOSS号。
     * 关于IOSS的含义、适用范围以及如何申请，请发件人咨询其DHL销售经理或DHL客服。
     *
     * 对于发往新西兰的部分快件，需要将该字段的值设置为SDT，相应的issuerCountryCode设置为目的地国家代码NZ，并在相应的number字段输入Overseas Registered Supplier(LVG)号码。
     * 同时需要搭配上述lineItems下的isTaxesPaid字段。关于新西兰的相关具体规定以及要求，请咨询DHL客服或DHL销售经理。
     *
     * 完整的可选值列表请参考英文原版YAML文档"
     */
    private String typeCode;

    /**
     * 发件人注册号/税号所属国国家代码
     * 即发件人的注册号/税号是在哪个国家申请注册的
     */
    private String issuerCountryCode;
}
