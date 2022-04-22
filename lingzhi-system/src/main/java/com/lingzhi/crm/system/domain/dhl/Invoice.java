package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 发票基础信息节点
 */
@Data
public class Invoice {

    /**
     * 发票日期
     * 2019-02-25
     */
    private String date;

    /**
     * 发票号码
     */
    private String number;

    /**
     * 清关发票签名
     */
    private String signatureName;

    /**
     * 签字人的职位
     */
    private String signatureTitle;

    /**
     * 签名影像（电子签名）的base64编码
     * "最大尺寸1MB，可用的影像格式: PNG, GIF, JPEG, JPG
     * 启用无纸化服务（PLT)时，建议添加该字段，从而在使用接口创建的清关发票影像上显示电子签名"
     */
    private String signatureImage;

    /**
     * 快件指令
     * 该字段的值仅会打印在使用COMMERCIAL_INVOICE_04模板制作的清关发票上。如果使用发票模板 COMMERCIAL_INVOICE_04, 建议将该字段的最大字符数控制在120个
     */
    private List<String> instructions;

    /**
     * 所寄全部商品的总净重（即所有lineItems的总净重）
     * 不建议在Request中添加该字段。因为后台会自动计算总净重
     */
    private BigDecimal totalNetWeight;

    /**
     * 所寄全部商品的总毛重（即所有lineItems的总毛重）
     * 不建议在Request中添加该字段。因为后台会自动计算总毛重
     */
    private BigDecimal totalGrossWeight;

    /**
     * 清关发票参考信息节点
     */
    private List<CustomerReference> customerReferences;

    /**
     * 支付条款
     * 为了在接口生成的清关发票上完整显示，建议将该字段的字符数控制在20个以内
     */
    private String termsOfPayment;
}
