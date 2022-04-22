package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 净重和毛重节点
 */
@Data
public class Weight {

    /**
     * 净重
     * 最多12位数，可保留3位小数
     */
    private BigDecimal netValue;

    /**
     * 毛重
     * 最多12位数，可保留3位小数
     */
    private BigDecimal grossValue;
}
