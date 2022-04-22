package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 发票备注节点
 */
@Data
public class Remark {

    /**
     * 发票备注信息
     * "
     * 如果使用清关发票模板COMMERCIAL_INVOICE_L_10或COMMERCIAL_INVOICE_P_10, 则发票上可以打印出全部
     * 三条RemarkDescription信息（建议将每条RemarkDescription的字符数控制在45个以内）"
     */
    private String value;
}
