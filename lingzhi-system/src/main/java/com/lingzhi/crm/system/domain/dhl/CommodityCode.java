package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 *  该节点用于录入单项物品的出口、进口海关编码（HS CODE)，只体现在清关发票上，不体现在运单上
 */
@Data
public class CommodityCode {

    /**
     * 单项商品的海关编码类别
     * "该字段的可选值为：
     *   - outbound 出口海关编码
     *   - inbound  进口海关编码"
     */
    private String typeCode;

    /**
     * 单项商品的海关编码
     */
    private String value;
}
