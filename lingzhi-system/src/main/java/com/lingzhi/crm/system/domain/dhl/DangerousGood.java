package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 危险品节点
 */
@Data
public class DangerousGood {

    /**
     * 危险品的代码
     * 请从危险品代码列表中选取；请同时在valueAddedServices节点添加相应的serviceCode（特殊服务代码），以保证快件信息完整传输
     */
    private String contentId;
}
