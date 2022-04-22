package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * ODD服务节点
 */
@Data
public class OnDemandDelivery {

    /**
     * 基于ODD服务的派送选项
     */
    private String deliveryOption;

    /**
     * 快件投送的最终位置
     */
    private String location;

    /**
     * 对于派送员的额外备注信息（有助于派送）
     */
    private String specialInstructions;

    /**
     * 进入派送地址公寓的门禁码
     */
    private String gateCode;

    /**
     * 派送给邻居的服务代码
     */
    private String whereToLeave;

    /**
     * 邻居的姓名
     */
    private String neighbourName;

    /**
     * 邻居的门牌号
     */
    private String neighbourHouseNumber;

    /**
     * 被授权人的姓名
     */
    private String authorizerName;

    /**
     * 指定的DHL服务点ID
     */
    private String servicePointId;

    /**
     * 要求派送日期
     */
    private String requestedDeliveryDate;

    /**
     * 是否需要返回ODD服务的URL
     */
    private String requestOndemandDeliveryURL;

}
