package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 发件人地址信息节点
 */
@Data
public class PostalAddress {

    /**
     * 发件人地址栏1
     * 发件人地址至少需要输入一行。如果addressLine1一行输入不下，可以继续在addressLine2和addressLine3输入
     */
    private String addressLine1;

    /**
     * 发件人地址栏2
     *
     */
    private String addressLine2;

    /**
     * 发件人地址栏3
     */
    private String addressLine3;

    /**
     * 发件人城市名称
     */
    private String cityName;

    /**
     * 发件人州代码
     * 主要用于填写美国的州二字代码
     */
    private String provinceCode;

    /**
     * 发件人州名/省名
     */
    private String provinceName;

    /**
     * 发件人邮编
     */
    private String postalCode;

    /**
     * 发件人国家代码
     */
    private String countryCode;

    /**
     * 发件人所在城市的郊区名
     */
    private String countyName;
}
