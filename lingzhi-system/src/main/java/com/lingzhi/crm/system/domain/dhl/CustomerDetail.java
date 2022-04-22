package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 客户信息节点
 * "customerDetails节点下共有5个并列的子节点（shipperDetails, receiverDetails, buyerDetails, exporterDetails, importerDetails)
 * 这5个子节点被分别用于录入不同类别的快件相关方信息，而且这5个子节点的架构和构成要素是基本相同的。
 * 这5个子节点中，shipperDetails, receiverDetails是必填的，
 * 其余3个选填（根据客户实际发件需求决定是否添加）
 * 这5个子节点中，每个子节点（即每类快件相关方）所适用的注册号/税号的可选值可能存在差异，详情请见如下每个子节点中NumberTypeCode字段的相关说明"
 */
@Data
public class CustomerDetail {

    /**
     * 发件人信息节点
     */
    private ShipperDetails shipperDetails;

    /**
     *  收件人信息节点
     */
    private ReceiverDetails receiverDetails;

    /**
     * 买家信息节点
     */
    private BuyerDetails buyerDetails;

    /**
     * 出口商信息节点
     */
    private ExporterDetails exporterDetails;

    /**
     * 进口商信息节点
     * 只有存在除了收、发件人以外的第三方进口商时，才需要考虑添加该字段。该字段的信息不显示在运单上，只显示在通过接口创建的清关发票上
     */
    private ImporterDetails importerDetails;

    /**
     * 卖家信息节点
     * "与shipperDetails节点基本相同
     * （除了registrationNumbers-typeCode字段的可选值与shipperDetails节点可能存在差异），
     * 请参考shipperDetails节点的组织架构、各下级字段的名称以及字符限制"
     */
    private ShipperDetails sellerDetails;

    /**
     * 付款方信息节点
     * "与shipperDetails节点基本相同
     * （除了registrationNumbers-typeCode字段的可选值与shipperDetails节点可能存在差异），
     * 请参考shipperDetails节点的组织架构、各下级字段的名称以及字符限制"
     */
    private ShipperDetails payerDetails;
}
