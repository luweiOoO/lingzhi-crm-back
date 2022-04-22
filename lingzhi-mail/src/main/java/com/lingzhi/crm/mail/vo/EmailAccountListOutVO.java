package com.lingzhi.crm.mail.vo;

import com.lingzhi.crm.common.annotation.Excel;

import java.io.Serializable;

/**
 * @Author: LuWei
 * @Description
 * @Date: Created in 17:58 2021/6/17
 * @Modified By:
 */
public class EmailAccountListOutVO implements Serializable {

    /** 邮箱id */
    private Long emailId;

    /** 邮箱地址 */
    private String emailAddress;

    /** 可用标识 1异常 2可用 */
    private String enableFlag;

    /** 绑定用户id */
    private Long userId;

    /** 绑定用户名称 */
    private String userName;

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "EmailAccountListOutVO{" +
                "emailId=" + emailId +
                ", emailAddress='" + emailAddress + '\'' +
                ", enableFlag='" + enableFlag + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
