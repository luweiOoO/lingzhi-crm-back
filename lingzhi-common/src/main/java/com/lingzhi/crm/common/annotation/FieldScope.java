package com.lingzhi.crm.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: LuWei
 * @Description 字段数据权限过滤
 * @Date: Created in 10:48 2022/2/15
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldScope {

}
