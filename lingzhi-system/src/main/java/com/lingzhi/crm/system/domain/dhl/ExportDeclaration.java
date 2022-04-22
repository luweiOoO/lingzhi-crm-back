package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.util.List;

/**
 * 海关申报节点
 * "当使用DHL接口创建清关发票时，清关发票主要通过此部分信息创建
 *
 * 对于包裹类运单，无论是否通过DHL接口创建清关发票，必须
 * 在Request中添加此节点，用以满足清关要求。"
 */
@Data
public class ExportDeclaration {

    /**
     * 目的地港名称
     * 不建议使用该字段，建议直接使用PlaceOfIncoterm字段录入具体的贸易条款所适用的港口名称
     */
    private String destinationPortName;

    /**
     * 出口商ID和出口商代码
     */
    private Exporter exporter;

    /**
     * 许可证信息录入节点
     */
    private List<License> licenses;

    /**
     * 出口类型
     * "可选值为如下，请发件人根据实际发货情况选择
     *     - permanent
     *     - temporary
     *     - return
     *     - used_exhibition_goods_to_origin
     *     - intercompany_use
     *     - commercial_purpose_or_sale
     *     - personal_belongings_or_personal_use
     *     - sample
     *     - gift
     *     - return_to_origin
     *     - warranty_replacement
     *     - diplomatic_goods
     *     - defence_material
     * "
     */
    private String exportReasonType;

    /**
     * 出口原因
     */
    private String exportReason;

    /**
     * 发票基础信息节点
     */
    private Invoice invoice;

    /**
     * 该字段用来录入具体的贸易条款所适用的港口名称（启运港、装运港、目的港等）
     * 该字段的值与PaymentInfo字段的值紧密关联，即在该字段录入哪类港口名称取决于PaymentInfo字段所选定的贸易条款。
     */
    private String placeOfIncoterm;

    /**
     * 发货目的
     * "可选值为：
     *  - personal
     *  - commercial
     * "
     */
    private String shipmentType;

    /**
     * 货物标识
     */
    private String packageMarks;

    /**
     * 支付方商品及服务税号/增值税号
     */
    private String payerVATNumber;

    /**
     * 收件人参考信息
     */
    private String recipientReference;

    /**
     * 清关单据清单节点
     */
    private List<CustomsDocument> customsDocuments;

    /**
     * 发票备注节点
     */
    private List<Remark> remarks;

    /**
     * 其他费用节点
     */
    private List<AdditionalCharge> additionalCharges;

    /**
     * 发票额外声明节点
     */
    private List<DeclarationNote> declarationNotes;

    /**
     * 单项商品的详细信息节点
     */
    private List<LineItem> lineItems;


}
