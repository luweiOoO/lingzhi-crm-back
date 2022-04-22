package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 运费以及税金支付账号节点
 */
@Data
public class Account {

    /**
     *  DHL账号类别（账号用途）
     *  "该字段的可选值为：
     * - shipper - 发件人
     * - payer - 运费支付方
     * - duties-taxes - 目的地税金支付方
     * 如果accounts节点只添加typeCode=shipper并录入相应的number(账号），则表示使用该发件人账号支付运费；
     * 如果如果accounts节点同时添加typeCode=shipper和typeCode=payer，并相应录入两个number(账号），则typeCode=payer所对应的number会作为支付运费的账号；
     * 如果accounts节点添加了typeCode=duties-taxes并录入相应的number(账号）,那么该number将被用于支付目的地税金。如果该number是发件人账号或第三国账号（发件人、收件人以外的国家），需要同时在valueAddedServices节点录入特殊服务代码DD，从而启用DTP服务。
     * 注：启用DTP会产生额外服务费用。
     * "
     */
    private String typeCode;

    /**
     * DHL账号
     * 该字段基于其所对应的typeCode发挥作用
     */
    private String number;
}
