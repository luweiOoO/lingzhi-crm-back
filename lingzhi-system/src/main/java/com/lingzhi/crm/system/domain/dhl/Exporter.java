package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 出口商ID和出口商代码
 */
@Data
public class Exporter {

    /**
     * 出口商ID
     */
    private String id;

    /**
     * 出口商代码
     * 出口商ID的类型代码，例如：DUN/SSN/EIN
     */
    private String code;
}
