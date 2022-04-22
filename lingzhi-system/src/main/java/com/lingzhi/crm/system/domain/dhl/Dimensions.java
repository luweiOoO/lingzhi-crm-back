package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 快件长宽高信息节点
 */
@Data
public class Dimensions {

    /**
     * 单件的长度
     */
    private BigDecimal length;

    /**
     * 单件的宽度
     */
    private BigDecimal width;

    /**
     * 单件的高度
     */
    private BigDecimal height;


}
