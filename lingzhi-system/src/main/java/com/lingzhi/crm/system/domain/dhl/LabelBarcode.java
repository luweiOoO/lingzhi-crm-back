package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 客户条码节点
 */
@Data
public class LabelBarcode {

    /**
     * 条形码的位置
     * "该字段的可选值为：
     *     - left
     *     - right"
     */
    private String position;

    /**
     * 条形码类型
     * "该字段的可选值为：
     *     - 93
     *     - 39
     *     - 128"
     */
    private String symbologyCode;

    /**
     * 条形码内容
     */
    private String content;

    /**
     * 条形码下的文字
     */
    private String textBelowBarcode;

}
