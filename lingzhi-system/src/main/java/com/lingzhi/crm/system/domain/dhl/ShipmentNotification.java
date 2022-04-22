package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 运单生成通知节点
 */
@Data
public class ShipmentNotification {

    /**
     * 运单生成通知节点
     */
    private String typeCode;

    /**
     * 电子邮件地址
     */
    private String receiverId;

    /**
     * 通知内容
     */
    private String bespokeMessage;

    /**
     * 通知语言
     */
    private String languageCode;

    /**
     * 通知语言所属国家代码
     */
    private String languageCountryCode;


}
