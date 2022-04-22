package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

import java.util.List;

/**
 * 运单/发票格式、样式节点
 */
@Data
public class OutputImageProperties {

    /**
     * 运单格式 从固定值中选取
     * pdf
     * 有效值有四个，分别为：pdf, zpl, lp2, epl  不添加此项将默认为pdf
     */
    private String encodingFormat;

    /**
     * 运单打印精度
     * 可选值：200，300
     */
    private int printerDPI;

    /**
     * 该节点可基于typeCode展开多个Object
     */
    private List<ImageOptions> imageOptions;

    /**
     * 客户条形码节点
     */
    private List<CustomerBarcodeParam> customerBarcodes;

    /**
     * 客户商标节点
     */
    private List<CustomerLogoParam> customerLogos;

    /**
     * 拆分成三份：运单转运联，底联，以及由收据+发票组成的PDF文件
     * 从固定值中选取
     */
    private boolean splitTransportAndWaybillDocLabels;

    /**
     * 不拆分，运单转运联，底联，Receipt以及发票集中在一个PDF文件下
     */
    private boolean allDocumentsInOneImage;

    /**
     * 适用于一票多件时，拆分为每个单独的Transport Label以及单独的运单底联
     */
    private boolean splitDocumentsByPages;

    /**
     * 拆分成三份：运单（转运联+底联），收据以及发票
     */
    private boolean splitInvoiceAndReceipt;
}
