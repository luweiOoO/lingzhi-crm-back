package com.lingzhi.crm.system.domain.dhl;

import java.math.BigDecimal;
import java.util.List;

/**
 * 单项商品的详细信息节点
 * "该节点用于录入单项商品的详情(单价，数量，英文品名，海关编码、原产地等)。
 * 该节点下的每个object表示一项（一种）商品，即多项（多种）商品需要用多个object分别表示。"
 */
public class LineItem {

    /**
     * 顺序号，用以区分每项商品
     * 整数1-999
     */
    private int number;

    /**
     * 单项商品的描述
     */
    private String description;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 单项商品的数量以及数量单位节点
     */
    private Quantity quantity;

    /**
     * 该节点用于录入单项物品的出口、进口海关编码（HS CODE)，只体现在清关发票上，不体现在运单上
     */
    private List<CommodityCode> commodityCodes;

    /**
     * 单项商品的出口类型
     * "可选值如下：
     *  - permanent
     *  - temporary
     *  - return
     *  - used_exhibition_goods_to_origin
     *  - intercompany_use
     *  - commercial_purpose_or_sale
     *  - personal_belongings_or_personal_use
     *  - sample
     *  - gift
     *  - return_to_origin
     *  - warranty_replacement
     *  - diplomatic_goods
     *  - defence_material
     * 请注意区分该字段与exportDeclaration节点下的同名下级字段：
     * exportDeclaration节点下的exportReasonType是用于说明整个快件的出口类型，
     * 而lineItems节点下的exportReasonType是用于说明快件中某项商品的出口类型"
     */
    private String exportReasonType;

    /**
     * 原产国国家代码
     */
    private String manufacturerCountry;

    /**
     *净重和毛重节点
     */
    private Weight weight;

    /**
     *发往新西兰的商品GST支付信息
     * "该字段仅适用于发往新西兰的部分商品。该字段用于在清关发票上显示所寄商品是否已支付GST(Goods and services tax)。
     * 该字段需要搭配下述shipperDetails节点中的registrationNumbers进行相关设置，详情请参考下述shipperDetails节点中的registrationNumbers字段的相关说明。
     * 关于新西兰的相关具体规定以及要求，请咨询客服或DHL账号经理。
     * 可选值：
     * true --- 已支付GST
     * false --- 未支付GST
     * Request中未添加该字段时，将默认为false"
     */
    private boolean isTaxesPaid;

    /**
     * 该项商品的包装信息补充说明
     * "该节点用于补充说明该商品的包装信息。需要与如下清关发票模板搭配才可以打印在清关发票上。
     * COMMERCIAL_INVOICE_P_10
     * COMMERCIAL_INVOICE_L_10
     * "
     */
    private List<String> additionalInformation;

    /**
     * "该节点基于typeCode最多可包含100个Object
     * 只有前5个Object所包含的参考信息可以打印在清关发票上（需要搭配发票模板
     * COMMERCIAL_INVOICE_P_10
     * 或COMMERCIAL_INVOICE_L_10）"
     */
    private List<CustomerReference> customerReferences;

    /**
     * 该节点基于typeCode最多可包含50个Object
     */
    private List<CustomsDocument> customsDocuments;
}
