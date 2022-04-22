package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 许可证信息录入节点
 */
@Data
public class License {

    /**
     * 许可证类型
     * "该字段的可选值为：
     *  - export
     *  - import"
     */
    private String typeCode;

    /**
     * 许可证号
     */
    private String value;
}
