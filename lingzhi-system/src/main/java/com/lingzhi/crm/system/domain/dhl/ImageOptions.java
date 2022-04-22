package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 可根据typeCode展开多个Object
 */
@Data
public class ImageOptions {

    /**
     * 单据类型 从固定值中选取
     * - label 运单转运联
     * - waybillDoc 运单底联
     * - invoice 清关发票
     * - receipt 收据（只适用于俄国）
     */
    private String typeCode;

    /**
     * 单据样式 从固定值中选取
     * "typeCode设为label时，该字段的常用可选值为：
     * ECOM26_84_A4_001：A4纸运单，
     * ECOM26_84_001：标签运单
     * Request中未添加该字段时，将默认为标签运单
     *
     * typeCode设为waybillDoc时，该字段的常用可选值为：
     * ARCH_8X4_A4_002：A4纸运单，
     * ARCH_8X4：标签运单
     * Request中未添加该元素时，将默认为标签运单
     *
     * typeCode设为invoice时，该字段的常用可选值为：
     * - COMMERCIAL_INVOICE_L_10
     * 该发票模板可横向打印出Shipper, Receiver, Buyer, Importer，Exporter以及Seller的相关信息（如果Request中完整提供了这些信息的话）
     *
     * - COMMERCIAL_INVOICE_P_10
     * 该发票模板可以纵向打印出Shipper,Receiver以及另外两种快件相关方的信息。
     * 当Request中存在五种快件相关方时（Shipper, Receiver, Buyer, Importer and Exporter），那么该发票模板将只打印Shipper, Receiver,  Importer 和Exporter这四类快件相关方的信息且返回的
     * Response中会有相关提示信息。
     * 当Request中存在六种快件相关方时（Shipper, Receiver, Buyer, Importer，Exporter and Seller），那么该发票模板将只打印Shipper, Receiver, Seller, Importer这四类快件相关方的信息且返回的
     * Response中会有相关提示信息。
     * "
     */
    private String templateName;

    /**
     * 是否生成此类单据 从固定值中选取
     * "该字段的可选值为：
     * true - 生成此类单据
     * false - 不生成此类单据
     * 该字段主要用于搭配typeCode=invoice时使用，用于设定是否创建清关发票。
     * typeCode=label时，不添加该字段将默认该字段为true
     * typeCode=waybillDoc且不启用PLT服务时，不添加该字段将默认该字段为true
     * typeCode=waybillDoc且启用PLT服务时，不添加该字段将默认该字段为false"
     */
    private boolean isRequested;

    /**
     * 是否在运单上隐藏DHL账号
     * "该字段专用于typeCode=waybillDoc时使用。该字段的可选值为：
     * true - 在运单上隐藏账号（请联系DHL账号经理/分区电子商
     * 务专员，事先与本地DHL作业确认是否可接受该隐藏账号的设置）
     * false - 在运单上体现账号（Request中未添加该元素时，将默认为""false""）"
     */
    private boolean hideAccountNumber;

    /**
     * 运单底联的份数 从固定值中选取
     * 该字段专用于typeCode=waybillDoc时使用。该字段的可选值为：1和2
     */
    private int numberOfCopies;

    /**
     * 清关发票类型 从固定值中选取
     * "该字段专用于typeCode=invoice时使用。该字段的可选值为：
     * - commercial 商业发票
     * - proforma 形式发票
     * 不添加该字段将默认为商业发票"
     */
    private String invoiceType;

    /**
     * 清关发票的语言 从固定值中选取
     * 该字段专用于typeCode=invoice时使用。该字段的默认值是eng，可支持的语言列表请参考Developer Guide
     */
    private String languageCode;

    /**
     * 是否在运单上打印DHL Logo
     * "该字段专用于typeCode=label或waybillDoc时使用。该字段的可选值为：
     * - true - 在运单上打印DHL Logo
     * - false - 不在运单上打印DHL Logo
     * Request中未添加该元素时，将默认为“true”
     * 该字段只能添加一个，或者加在label下，或者加在waybillDoc下，不能重复添加。"
     */
    private boolean renderDHLLogo;

    /**
     * 该字段可用于在运单转运联上备注快件信息
     * 该字段专用于typeCode=label时使用
     */
    private String labelCustomerDataText;

    /**
     * 该字段可用于在运单转运联上备注快件信息
     * 该字段专用于typeCode=label时使用
     */
    private String labelFreeText;
}
