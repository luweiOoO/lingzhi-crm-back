package com.lingzhi.crm.order.dto;

import com.lingzhi.crm.order.domin.ProductSmallOrder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: LuWei
 * @Description 查询生产指示单入参类
 * @Date: Created in 11:32 2022/2/14
 * @Modified By:
 */
@Data
public class SmallOrderSearchParamsDto extends ProductSmallOrder {

    /** 起始下单日期 */
    private Date startOrderDate;

    /** 截至下单日期 */
    private Date endOrderDate;

    /** 起始计划发货日期 */
    private Date startPlanDeliveryDate;

    /** 截至计划发货日期 */
    private Date endPlanDeliveryDate;
}
