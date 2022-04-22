package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 转运联客户备注信息节点
 */
@Data
public class LabelText {

    /**
     * 备注信息的位置
     * "该字段的可选值为：
     *     - left
     *     - right"
     */
    private String position;

    /**
     * 备注信息的标题
     */
    private String caption;

    /**
     * 备注信息的内容
     */
    private String value;
}
