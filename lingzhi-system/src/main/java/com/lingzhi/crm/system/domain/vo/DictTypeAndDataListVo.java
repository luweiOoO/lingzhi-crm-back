package com.lingzhi.crm.system.domain.vo;

import com.lingzhi.crm.common.core.domain.entity.SysDictData;
import lombok.Data;

import java.util.List;

/**
 * @Author: LuWei
 * @Description
 * @Date: Created in 10:17 2022/2/16
 * @Modified By:
 */
@Data
public class DictTypeAndDataListVo {

    private static final long serialVersionUID = 1L;

    /**
     * 字典主键
     */
    private Long dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 字典数据列表
     */
    private List<SysDictData> sysDictDataList;

}
