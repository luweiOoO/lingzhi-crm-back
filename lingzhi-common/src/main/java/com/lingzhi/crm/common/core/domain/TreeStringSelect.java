package com.lingzhi.crm.common.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lingzhi.crm.common.core.domain.entity.SysDept;
import com.lingzhi.crm.common.core.domain.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: LuWei
 * @Description
 * @Date: Created in 13:28 2022/2/16
 * @Modified By:
 */
@Data
public class TreeStringSelect implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 节点Code */
    private String code;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeStringSelect> children;


    public TreeStringSelect(){}

    /**
     * 构建数据表信息
     * @param fieldResultMapVo
     */
    public TreeStringSelect(Map.Entry<String, List<DateFieldResultVo>> fieldResultMapVo){
        List<DateFieldResultVo> fieldList = fieldResultMapVo.getValue();
        this.code = fieldList.get(0).getTableName();
        this.label = fieldList.get(0).getTableComment();
        List<TreeStringSelect> children = new LinkedList<>();
        fieldList.forEach(field -> {
            TreeStringSelect childrenNode = new TreeStringSelect();
            childrenNode.setCode(field.getColumnName());
            childrenNode.setLabel(field.getColumnComment());
            children.add(childrenNode);
        });
        this.children = children;
    }

}
