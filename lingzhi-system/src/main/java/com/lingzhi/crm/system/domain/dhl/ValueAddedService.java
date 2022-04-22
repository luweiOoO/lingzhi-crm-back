package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.util.List;

/**
 * 特殊/增值服务节点
 */
@Data
public class ValueAddedService {

    /**
     * 特殊/增值服务代码
     * "如保险服务代码II，DTP服务代码DD，PLT服务代码WY等，详见Reference_Data表格中的Special Service Code页面
     * 特殊/增值服务可能会产生额外服务费用，费用详情请咨询DHL销售经理"
     */
    private String serviceCode;

    /**
     * 特殊/增值服务价值（当前主要用于填写保险价值）
     * 最大18位数，最多保留2位小数
     */
    private int value;

    /**
     * 特殊/增值服务的货币单位
     * 保险服务的货币单位通常设为CNY
     */
    private String currency;

    /**
     * 危险品节点
     * 发送锂电池类快件需要添加此节点
     */
    private List<DangerousGood> dangerousGoods;
}
