package com.lingzhi.crm.mail.vo;

import java.io.Serializable;

/**
 * @Author: LuWei
 * @Description 邮箱账户改绑请求参数
 * @Date: Created in 14:26 2021/6/18
 * @Modified By:
 */
public class EmailAccountBindChangeInVO implements Serializable {

    /**
     * 邮箱id
     */
    private Long emailId;

    /**
     * 用户id
     */
    private Long userId;

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "EmailAccountBindChangeInVO{" +
                "emailId=" + emailId +
                ", userId=" + userId +
                '}';
    }
}
