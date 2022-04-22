package com.lingzhi.crm.common.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingzhi.crm.common.utils.DateUtils;
import com.lingzhi.crm.common.utils.SecurityUtils;
import com.lingzhi.crm.common.utils.StringUtils;
import lombok.Data;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
@Data
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 搜索值 */
    private String searchValue;

    /** 创建者 */
    private String createBy;

    private Long createId;

    private String createName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    private Long updateId;

    private String updateName;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    /**
     * 删除标记 Y已删除 N未删除
     */
    private String delFlag;

    /** 请求参数 */
    private Map<String, Object> params;

    public void setCreateInfo(){
        String username = SecurityUtils.getUsername();
        if (StringUtils.isNotBlank(username)) {
            if (StringUtils.isBlank(this.getCreateBy())) {
                this.setCreateBy(username);
            }
            if (StringUtils.isBlank(this.getCreateName())) {
                this.setCreateName(username);
            }

//            if (this.createId == null) {
//                this.setCreateId(SecurityUtils.getUserId());
//            }
//
            if (this.delFlag == null) {
                this.setDelFlag("N");
            }
        }

        this.setCreateTime(DateUtils.getNowDate());
    }

    public void setUpdateInfo(){
        String username = SecurityUtils.getUsername();
        if (StringUtils.isNotBlank(username)) {
            if (StringUtils.isBlank(this.getUpdateBy())) {
                this.setUpdateBy(username);
            }
            if (StringUtils.isBlank(this.getUpdateName())) {
                this.setUpdateName(username);
            }
        }

        this.setUpdateTime(DateUtils.getNowDate());
    }




    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }


}
