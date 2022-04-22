package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 客户商标信息类
 */
@Data
public class CustomerLogoParam {

    /**
     * 客户商标影像的类型 从固定值中选取
     * "可选值：
     * - GIF
     * - JPEG
     * - JPG
     * - PNG
     * "
     */
    private String fileFormat;


    /**
     * 客户商标的base64编码流
     * "可支持的影像格式：
     * - RGB
     * - B&W, Black and White
     * 最大1 MB"
     */
    private String content;
}
