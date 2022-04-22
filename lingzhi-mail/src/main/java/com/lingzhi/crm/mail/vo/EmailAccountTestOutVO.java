package com.lingzhi.crm.mail.vo;

import java.io.Serializable;

/**
 * @Author: LuWei
 * @Description 邮箱检测结果参数类
 * @Date: Created in 17:52 2021/6/22
 * @Modified By:
 */
public class EmailAccountTestOutVO implements Serializable {

    /**
     * 邮箱密码检测结果
     */
    private Boolean passwordResult;

    /**
     * 收邮件服务器检测结果
     */
    private Boolean sendHostResult;

    /**
     * 发邮件服务器检测结果
     */
    private Boolean receiveHostResult;

    public Boolean getPasswordResult() {
        return passwordResult;
    }

    public void setPasswordResult(Boolean passwordResult) {
        this.passwordResult = passwordResult;
    }

    public Boolean getSendHostResult() {
        return sendHostResult;
    }

    public void setSendHostResult(Boolean sendHostResult) {
        this.sendHostResult = sendHostResult;
    }

    public Boolean getReceiveHostResult() {
        return receiveHostResult;
    }

    public void setReceiveHostResult(Boolean receiveHostResult) {
        this.receiveHostResult = receiveHostResult;
    }

    @Override
    public String toString() {
        return "EmailAccountTestOutVO{" +
                "passwordResult=" + passwordResult +
                ", sendHostResult=" + sendHostResult +
                ", receiveHostResult=" + receiveHostResult +
                '}';
    }
}
