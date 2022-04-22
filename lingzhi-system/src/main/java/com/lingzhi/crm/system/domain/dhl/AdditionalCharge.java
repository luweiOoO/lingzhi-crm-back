package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 其他费用节点
 */
@Data
public class AdditionalCharge {

    /**
     * 费用名称
     * 该字段用于录入客户自定义的费用名称。一般不用添加caption，因为可以通过如下的typeCode字段选择具体的费用类型
     */
    private String caption;

    /**
     * 费用金额
     */
    private BigDecimal value;

    /**
     * 费用类型
     * "常用可选值为(逗号左边是可选值，逗号右边是可选值的含义）：
     * - admin, administration fee
     * - delivery, delivery fee
     * - documentation, documentation fee
     * - expedite, expedite fee
     * - export, export fee
     * - freight , freight cost
     * - fuel_surcharge, fuel surcharge
     * - logistic, logistic fee
     * - other , other fee
     * - packaging, packaging/packing fee
     * - pickup, pickup fee
     * - handling, handling fee
     * - vat, VAT fee
     * - insurance , insurance cost
     * 更多详情请查阅英文原版YAML文档"
     */
    private String typeCode;
}
