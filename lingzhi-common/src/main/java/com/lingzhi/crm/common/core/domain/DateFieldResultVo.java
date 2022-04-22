package com.lingzhi.crm.common.core.domain;

import lombok.Data;

/**
 * @Author: LuWei
 * @Description
 * @Date: Created in 17:25 2022/2/15
 * @Modified By:
 */
@Data
public class DateFieldResultVo {

    /** 数据表名 */
    private String tableName;

    /** 数据表备注 */
    private String tableComment;

    /** 字段名 */
    private String columnName;

    /** 字段备注 */
    private String columnComment;
}
