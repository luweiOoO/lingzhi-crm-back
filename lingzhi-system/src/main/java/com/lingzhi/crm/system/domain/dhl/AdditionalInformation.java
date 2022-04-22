package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 额外的快件信息信息
 */
@Data
public class AdditionalInformation {

    /**
     * 额外快件信息的类别
     * "该字段的可选值为：
     *  - pickupDetails
     *  - optionalShipmentData
     *  - barcodeInformation"
     */
    private String typeCode;

    /**
     * 是否返回此类信息
     * 可选值为：true和false
     */
    private boolean isRequested;
}
