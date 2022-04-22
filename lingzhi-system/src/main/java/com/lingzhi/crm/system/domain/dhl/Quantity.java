package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 单项商品的数量以及数量单位节点
 */
@Data
public class Quantity {

    /**
     * 单项商品的数量
     * 该字段只能录入整数
     */
    private int value;

    /**
     * 数量单位
     * "常用的可选值如下：(左边是可选值，右边是可选值的含义。
     * 例如，2GM是可选值，Centigram是可选值2GM的含义，以此类推)
     * - 2GM Centigram
     * - BOX Boxes
     * - 2M Centimeters
     * - 2M3 Cubic Centimeters
     * - 3M3 Cubic Feet
     * - M3 Cubic Meters
     * - DPR Dozen Pairs
     * - DOZ Dozen
     * - 2NO Each
     * - PCS Pieces
     * - GM Grams
     * - GRS Gross
     * - KG Kilograms
     * - L Liters
     * - M Meters
     * - 3GM Milligrams
     * - 3L Milliliters
     * - X No Unit Required
     * - NO Number
     * - 2KG Ounces
     * - PRS Pairs
     * - 2L Gallons
     * - 3KG Pounds
     * - CM2 Square Centimeters
     * - 2M2 Square Feet
     * - 3M2 Square Inches
     * - M2 Square Meters
     * - 4M2 Square Yards
     * - CM Centimeters
     * - CONE
     * - CT
     * - EA Each
     * - LBS Pounds
     * - RILL
     * - ROLL Roll
     * - SET
     * - TU
     * - YDS Yards"
     */
    private String unitOfMeasurement;
}
