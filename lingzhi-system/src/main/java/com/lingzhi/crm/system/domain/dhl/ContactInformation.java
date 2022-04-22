package com.lingzhi.crm.system.domain.dhl;

import lombok.Data;

/**
 * 发件人联系方式信息节点
 */
@Data
public class ContactInformation {

    /**
     * 发件人联系人姓名
     */
    private String fullName;

    /**
     * 发件人公司名称
     */
    private String companyName;

    /**
     * 发件人电话号码
     */
    private String phone;

    /**
     * 发件人电子邮箱
     */
    private String email;

    /**
     * 发件人手机号码
     */
    private String mobilePhone;
}
