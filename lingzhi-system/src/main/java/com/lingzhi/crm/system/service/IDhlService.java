package com.lingzhi.crm.system.service;

import com.lingzhi.crm.system.domain.dhl.CreateShipmentRequestParam;

/**
 * DHL相关服务接口
 */
public interface IDhlService {

    /**
     * 创建运单
     * @param param
     * @return
     */
    Object createShipment(CreateShipmentRequestParam param);
}
