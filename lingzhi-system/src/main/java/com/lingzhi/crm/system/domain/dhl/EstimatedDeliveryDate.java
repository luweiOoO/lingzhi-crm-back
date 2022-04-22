package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 预计派送日期
 */
@Data
public class EstimatedDeliveryDate {

    /**
     * 是否返回预计派送日期
     */
    private boolean isRequested;

    /**
     * 预计派送日期类型
     * "可选值为：
     * QDDC – 会考虑潜在的影响快件转运时效的相关
     * 因素（例如清关等）
     * QDDF – 不考虑清关等影响快件转运时效的相关因素，返回理论上
     * 最快的派送日期"
     */
    private String typeCode;
}
