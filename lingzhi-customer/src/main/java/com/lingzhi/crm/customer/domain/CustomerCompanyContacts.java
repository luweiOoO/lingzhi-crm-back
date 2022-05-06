package com.lingzhi.crm.customer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lingzhi.crm.common.annotation.Excel;
import com.lingzhi.crm.common.core.domain.BaseEntity;

/**
 * 客户公司联系人信息对象 customer_company_contacts
 * 
 * @author ruoyi
 * @date 2021-06-03
 */
@Data
public class CustomerCompanyContacts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 所属公司id */
    @Excel(name = "所属公司id")
    private Long customerCompanyId;

    /** 姓名 */
    @Excel(name = "名称")
    private String contactName;

    /** 昵称 */
    @Excel(name = "昵称")
    private String contactNickName;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String contactEmail;

    /** 职级key */
    @Excel(name = "职级key")
    private String contactRankKey;

    /** 职位 */
    @Excel(name = "职位")
    private String contactPosition;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contactBirthday;

    /** 性别key */
    @Excel(name = "性别key")
    private String contactGenderKey;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imageUrl;

    /** 联系人住址 */
    @Excel(name = "联系人住址")
    private String contactAddress;

    /** 联系人邮箱或电话 */
    @Excel(name = "联系人邮箱或电话")
    private String contactEamilPhone;

    /** 创建人昵称 */
    @Excel(name = "创建人昵称")
    private String createdUserName;

    /** 创建人账号 */
    @Excel(name = "创建人账号")
    private String createdUserAccount;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人昵称 */
    @Excel(name = "更新人昵称")
    private String updatedUserName;

    /** 更新人账号 */
    @Excel(name = "更新人账号")
    private String updatedUserAccount;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 删除标志 0未删除 1已删除 */
    @Excel(name = "删除标志 0未删除 1已删除")
    private Integer isDelete;

    /** 所属租户id */
    @Excel(name = "所属租户id")
    private Long tenantId;
}
