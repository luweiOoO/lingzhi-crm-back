package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 制作运单+预约取件---包裹类请求参数
 */
@Data
public class CreateShipmentRequestParam implements Serializable {

    /**
     * 发件日期
     * 启用预约取件服务时也会调用该字段的值作为快件备妥时间
     * 参考值 2022-04-20 21:24:35
     */
    private String plannedShippingDateAndTime;

    /**
     * 预约取件节点
     */
    private ShipmentPickupParam pickup;

    /**
     * 是否返回ODD网址链接 true/false
     */
    private Boolean requestOndemandDeliveryURL;

    /**
     * 是否返回预估运费 true/false
     */
    private Boolean getRateEstimates;

    /**
     * global 产品编码
     * "普通包裹：P，正午特派包裹：Y
     * 更多详情，请查看开发包中Reference_Data中的
     * Global Product Codes页面"
     */
    private String productCode;

    /**
     * 本地产品编码
     * "对于CN来说，localProductCode通常与productCode一致。但对于某些国家/某些产品，两者可能存在差异，这取决于当地DHL的设定。
     * 虽然localProductCode是选填字段，但出于数据完整传输考虑，建议在Request中保留localProductCode，一起传输。"
     */
    private String localProductCode;

    /**
     * 运单/发票格式、样式节点
     */
    private OutputImageProperties outputImageProperties;

    /**
     * 返回额外的快件信息
     */
    private List<AdditionalInformation> getAdditionalInformation;

    /**
     * 预计派送日期
     */
    private EstimatedDeliveryDate estimatedDeliveryDate;

    /**
     * 运费及税金支付账号节点
     */
    private List<Account> accounts;

    /**
     * 快件参考信息节点
     */
    private List<CustomerReference> customerReferences;

    /**
     * 特殊/增值 服务节点
     */
    private List<ValueAddedService> valueAddedServices;

    /**
     * 无纸化单据影像上传节点
     */
    private List<DocumentImage> documentImages;

    /**
     * 快件详情节点
     */
    private ShipmentContent content;

    /**
     * 客户信息节点
     */
    private CustomerDetail customerDetails;

    /**
     * 运单生成通知节点
     */
    private List<ShipmentNotification> shipmentNotification;

    /**
     * 若启用ODD服务，需同时添加Buyer节点。关于开通ODD服务，请咨询DHL销售经理
     */
    private OnDemandDelivery onDemandDelivery;

}
