package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.util.List;

/**
 *预约取件请求参数类
 */
@Data
public class ShipmentPickupParam {

    /**
     * 是否启用预约取件功能
     * 设为false：不预约取件，只制作运单
     * 设为true：启用预约取件服务，同时制作运单和预约取件
     */
    private boolean isRequested;

    /**
     * 当日最晚取件时间
     * 建议启用预约取件服务时添加该字段
     * 17:00
     */
    private String closeTime;

    /**
     * 具体取件处
     */
    private String location;

    /**
     * 取件特殊说明节点
     */
    private List<SpecialInstruction> specialInstructions;

    /**
     * 预约取件地址节点
     */
    private ShipperDetails pickupDetails;

    /**
     * 远程取件预约请求方信息
     */
    private ShipperDetails pickupRequestorDetails;

}
