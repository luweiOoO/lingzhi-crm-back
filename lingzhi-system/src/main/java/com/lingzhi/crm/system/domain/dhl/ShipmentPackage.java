package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 所寄快件每一件（即每一箱）的重量、尺寸节点
 */
@Data
public class ShipmentPackage {

    /**
     * 快件单件的重量（即单箱重量）
     * 由于当前运单上的重量只显示1位小数，所以建议将重量控制在1位小数，以免运单上显示的重量不完整
     */
    private BigDecimal weight;

    /**
     * 单件货物的内容描述
     */
    private String description;

    /**
     * 包装类型代码
     * 请参考原始YAML文档查看完整的包装类型代码列表
     */
    private String typeCode;

    /**
     * 快件长宽高信息节点
     */
    private Dimensions dimensions;

    /**
     * 客户条形码节点
     * "该节点用于录入客户自定义的条形码，可体现在运单转运联上。
     * 该节点必须搭配使用‘ECOM26_84CI_003’作为label的templateName
     * 相对前述的outputImageProperties中的customerBarcodes，两者只能二选一，因为两者使用的运单模板不相同，请根据实际需求选择"
     */
    private List<LabelBarcode> labelBarcodes;

    /**
     * 转运联客户备注信息节点
     */
    private List<LabelText> labelText;

    /**
     * 客户的额外信息备注
     * 该节点必须搭配使用‘ECOM26_84CI_003’作为label的templateName
     */
    private String labelDescription;

    /**
     * 单件参考信息节点
     */
    private List<CustomerReference> customerReferences;
}
