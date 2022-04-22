package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 无纸化单据影像上传节点
 */
@Data
public class DocumentImage {

    /**
     *单据影像类型
     * "可从如下值中选取 (逗号左边是可选值，逗号右边是可选值的含义）
     * - INV, Invoice
     * - PNV, Proforma
     * - COO, Certificate of Origin
     * - NAF, Nafta Certificate of Origin
     * - CIN, Commercial Invoice
     * - DCL, Custom Declaration
     * - AWB, Air Waybill and Waybill Document"
     */
    private String typeCode;

    /**
     * 单据影像Base64编码
     */
    private String content;

    /**
     * 单据影像格式
     * "Possible values;
     * - PDF
     * - PNG
     * - TIFF
     * - GIF
     * - JPEG"
     */
    private String imageFormat;

}
