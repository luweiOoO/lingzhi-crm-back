package com.lingzhi.crm.system.domain;

import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色过滤字段权限对象 sys_role_filter_field
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Data
public class SysRoleFilterField extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色id */
    @Excel(name = "角色id")
    private Long roleId;

    /** 数据表名称 */
    @Excel(name = "数据表名称")
    private String dataTableName;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String fieldName;

}
