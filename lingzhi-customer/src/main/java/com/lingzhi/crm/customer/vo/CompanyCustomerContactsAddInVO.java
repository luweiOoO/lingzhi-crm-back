package com.lingzhi.crm.customer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingzhi.crm.customer.domain.CustomerContactsPhone;
import com.lingzhi.crm.customer.domain.CustomerContactsSocialPlatform;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: LuWei
 * @Description客户公司联系人新增接口入参类
 * @Date: Created in 11:04 2021/6/3
 * @Modified By:
 */
@Data
public class CompanyCustomerContactsAddInVO implements Serializable {

    /**
     * 名称
     */
    private String contactName;

    /** 昵称 */
    private String contactNickName;

    /** 邮箱 */
    private String contactEmail;

    /** 职级key */
    private String contactRankKey;

    /** 职位 */
    private String contactPosition;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contactBirthday;

    /** 性别key */
    private String contactGenderKey;

    /** 备注 */
    private String remarks;

    /** 图片地址 */
    private String imageUrl;

    /** 联系人住址 */
    private String contactAddress;

    /** 联系人邮箱或电话 */
    private String contactEamilPhone;

    /** 联系人电话List */
    private List<CustomerContactsPhone> contactsPhoneList;

    /** 联系人社交平台List */
    private List<CustomerContactsSocialPlatform> contactsSocialPlatformList;

}
