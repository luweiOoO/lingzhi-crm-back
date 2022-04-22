package com.lingzhi.crm.order.dto;

import com.lingzhi.crm.order.domin.ProductWaybillMessage;
import lombok.Data;

import java.util.Date;

/**
 * @Author: LuWei
 * @Description
 * @Date: Created in 14:20 2022/2/14
 * @Modified By:
 */
@Data
public class WaybillMessageParamsDto extends ProductWaybillMessage {

    /** 开始发货日期 */
    private Date startDeliveryDate;

    /** 截至发货日期 */
    private Date endDeliveryDate;
}
