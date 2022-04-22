package com.lingzhi.crm.customer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingzhi.crm.customer.domain.CustomerContactsPhone;
import com.lingzhi.crm.customer.domain.CustomerContactsSocialPlatform;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: LuWei
 * @Description客户公司联系人新增接口入参类
 * @Date: Created in 11:04 2021/6/3
 * @Modified By:
 */
public class CompanyCustomerContactsAddInVO implements Serializable {

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

    public String getContactNickName() {
        return contactNickName;
    }

    public void setContactNickName(String contactNickName) {
        this.contactNickName = contactNickName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactRankKey() {
        return contactRankKey;
    }

    public void setContactRankKey(String contactRankKey) {
        this.contactRankKey = contactRankKey;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public Date getContactBirthday() {
        return contactBirthday;
    }

    public void setContactBirthday(Date contactBirthday) {
        this.contactBirthday = contactBirthday;
    }

    public String getContactGenderKey() {
        return contactGenderKey;
    }

    public void setContactGenderKey(String contactGenderKey) {
        this.contactGenderKey = contactGenderKey;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactEamilPhone() {
        return contactEamilPhone;
    }

    public void setContactEamilPhone(String contactEamilPhone) {
        this.contactEamilPhone = contactEamilPhone;
    }

    public List<CustomerContactsPhone> getContactsPhoneList() {
        return contactsPhoneList;
    }

    public void setContactsPhoneList(List<CustomerContactsPhone> contactsPhoneList) {
        this.contactsPhoneList = contactsPhoneList;
    }

    public List<CustomerContactsSocialPlatform> getContactsSocialPlatformList() {
        return contactsSocialPlatformList;
    }

    public void setContactsSocialPlatformList(List<CustomerContactsSocialPlatform> contactsSocialPlatformList) {
        this.contactsSocialPlatformList = contactsSocialPlatformList;
    }

    @Override
    public String toString() {
        return "CompanyCustomerContactsAddInVo{" +
                "contactNickName='" + contactNickName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactRankKey='" + contactRankKey + '\'' +
                ", contactPosition='" + contactPosition + '\'' +
                ", contactBirthday=" + contactBirthday +
                ", contactGenderKey='" + contactGenderKey + '\'' +
                ", remarks='" + remarks + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", contactEamilPhone='" + contactEamilPhone + '\'' +
                ", contactsPhoneList=" + contactsPhoneList +
                ", contactsSocialPlatformList=" + contactsSocialPlatformList +
                '}';
    }
}
