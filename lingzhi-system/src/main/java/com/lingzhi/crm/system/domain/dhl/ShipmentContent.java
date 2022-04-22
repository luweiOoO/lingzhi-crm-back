package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.util.List;

/**
 * 快件详情节点
 */
@Data
public class ShipmentContent {

    /**
     * 所寄快件的类别：包裹/文件
     * "可选值为：true   包裹
     *          false   文件"
     */
    private boolean isCustomsDeclarable;

    /**
     * 货物描述
     * "该字段的值对应运单上的货物描述部分
     * 请在该字段使用英文准确地录入所寄快件的货物描述"
     */
    private String description;

    /**
     * 当Request中未添加exportDeclaration字段时，该字段的值将作为包裹快件的申报价值
     *
     * "对于包裹类快件，该字段的值需大于0且该字段的值应等于（所有lineItems的价值之和+所有additionalCharges之和）。
     *
     * 当Request中添加了exportDeclaration字段时,  declaredValue字段的
     * 值将被直接忽略，后台将直接采用Request中全部lineItems的总价值+全部additionalCharges之和作为申报价值。该申报价值将体现在接口生成的运单底联（WAYBILL DOC）上以及接口生成的清关
     * 发票上，且该申报价值将被传输至清关系统向海关申报。"
     */
    private int declaredValue;

    /**
     * 货币单位
     * "该字段的值既是declaredValue字段的货币单位，同时也
     * 是exportDeclaration字段中的下级字段additionalCharges、lineItems字段中的下级字段price的货币单位。
     * 该字段常用的可选值为：
     * -USD 美元
     * -EUR 欧元
     * -CNY 人民币
     * -JPY  日元
     * -GBP 英镑
     * 完整的可选值列表可查阅开发包中的Referene_Data表格"
     */
    private String declaredValueCurrency;

    /**
     * 重量单位
     * "可选值有两个：metric表示千克和厘米组合；imperial表示磅和英寸组合
     * 该字段的值既是packages字段的重量单位，同时也
     * 是lineItems字段中的下级字段weight的重量单位"
     */
    private String unitOfMeasurement;

    /**
     * 贸易条款
     * "请客户根据其实际业务设定贸易条款。常用可选值为：(例如CFR是可选值，Cost And Freight是CFR的含义，以此类推)
     * - CFR – Cost And Freight
     * - CIF – Cost, Insurance and Freight
     * - CIP – Carriage And Insurance Paid To
     * - CPT – Carriage Paid To
     * - DAF – Delivered At Frontier
     * - DDP – Delivery Duty Paid
     * - DDU – Delivery Duty Unpaid
     * - DAP – Delivered At Place
     * - DAT – Delivered At Terminal
     * - DEQ – Delivered Ex Quay (Duty Paid)
     * - DES – Delivered Ex Ship
     * - EXW – Ex Works
     * - FAS – Free Alongside Ship
     * - FCA – Free Carrier
     * - FOB – Free On Board
     * - DPU – Delivered at Place Unloaded"
     */
    private String incoterm;

    /**
     * 美国出口备案类型
     * 只有从美国发出的快件才会用到
     */
    private String USFilingTypeValue;

    /**
     * 海关申报节点
     * "当使用DHL接口创建清关发票时，清关发票主要通过此部分信息创建
     *
     * 对于包裹类运单，无论是否通过DHL接口创建清关发票，必须
     * 在Request中添加此节点，用以满足清关要求。"
     */
    private ExportDeclaration exportDeclaration;

    /**
     * 所寄快件每一件（即每一箱）的重量、尺寸节点
     */
    private List<ShipmentPackage> packages;
}
