package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 快件参考信息节点
 */
@Data
public class CustomerReference {

    /**
     * 快件参考信息内容
     */
    private String value;

    /**
     * 快件参考信息类型
     * "该字段常用的可选值为：CU，表示reference number of consignor
     * Request中不添加该字段时，将默认该字段的值为CU
     * 该字段的完整可选值列表请参考YAML文档"
     *
     * "该字段常用的可选值为（逗号左边是可选值，逗号右边是可选值的含义）：
     * - ACL, Parent Shipment ID for BBX
     * - CID, Customer Identifier
     * - CN, Contract Number
     * - CU, Consignor reference number
     * - ITN, US Export declaration reference ID
     * - MRN, Movement Reference number
     * - UCN, Unique reference of a consignment (UCRN)
     * - OID, Order Number
     * - PON, Purchase Order Number
     * 该字段完整的可选值列表请查看英文原版YAML文档"
     */
    private String typeCode;

}
