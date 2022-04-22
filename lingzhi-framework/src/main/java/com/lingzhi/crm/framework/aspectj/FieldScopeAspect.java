package com.lingzhi.crm.framework.aspectj;

import com.lingzhi.crm.common.annotation.FieldScope;
import com.lingzhi.crm.common.core.domain.BaseEntity;
import com.lingzhi.crm.common.core.domain.entity.SysRole;
import com.lingzhi.crm.common.core.domain.entity.SysUser;
import com.lingzhi.crm.common.core.domain.model.LoginUser;
import com.lingzhi.crm.common.utils.ServletUtils;
import com.lingzhi.crm.common.utils.StringUtils;
import com.lingzhi.crm.common.utils.spring.SpringUtils;
import com.lingzhi.crm.framework.web.service.TokenService;
import com.lingzhi.crm.system.service.ISysRoleFilterFieldService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LuWei
 * @Description 字段数据权限切面
 * @Date: Created in 10:49 2022/2/15
 * @Modified By:
 */
@Aspect
@Component
public class FieldScopeAspect {

    /**
     * 字段权限过滤关键字
     */
    public static final String FIELD_SCOPE = "fieldScope";

    @Autowired
    private ISysRoleFilterFieldService roleFilterFieldService;

    // 配置织入点
    @Pointcut("@annotation(com.lingzhi.crm.common.annotation.FieldScope)")
    public void fieldScopePointCut() {
    }


    @Before("fieldScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable
    {
        handleFieldScope(point);
    }

    protected void handleFieldScope(final JoinPoint joinPoint)
    {
        // 获得注解
        FieldScope controllerFieldScope = getAnnotationLog(joinPoint);
        if (controllerFieldScope == null)
        {
            return;
        }
        // 获取当前的用户
        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNotNull(loginUser))
        {
            SysUser currentUser = loginUser.getUser();
            // 如果是超级管理员，则不过滤数据
            if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin())
            {
                fieldScopeFilter(joinPoint, currentUser);
            }
        }
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param user 用户
     */
    public void fieldScopeFilter(JoinPoint joinPoint, SysUser user)
    {
        List<String> fieldList = new ArrayList<>();
        for(SysRole role : user.getRoles()){
            fieldList.addAll(roleFilterFieldService.getFilterFieldNameListByRoleId(role.getRoleId()));
        }
        if(fieldList.size() > 0){
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseEntity)
            {
                BaseEntity baseEntity = (BaseEntity) params;
                baseEntity.getParams().put(FIELD_SCOPE, fieldList);
            }
        }
//        StringBuilder sqlString = new StringBuilder();

//        for (SysRole role : user.getRoles())
//        {
//            String dataScope = role.getDataScope();
//            if (DATA_SCOPE_ALL.equals(dataScope))
//            {
//                sqlString = new StringBuilder();
//                break;
//            }
//            else if (DATA_SCOPE_CUSTOM.equals(dataScope))
//            {
//                sqlString.append(StringUtils.format(
//                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ", deptAlias,
//                        role.getRoleId()));
//            }
//            else if (DATA_SCOPE_DEPT.equals(dataScope))
//            {
//                sqlString.append(StringUtils.format(" OR {}.dept_id = {} ", deptAlias, user.getDeptId()));
//            }
//            else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope))
//            {
//                sqlString.append(StringUtils.format(
//                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ) )",
//                        deptAlias, user.getDeptId(), user.getDeptId()));
//            }
//            else if (DATA_SCOPE_SELF.equals(dataScope))
//            {
//                if (StringUtils.isNotBlank(userAlias))
//                {
//                    sqlString.append(StringUtils.format(" OR {}.create_name = '{}' ", userAlias, user.getUserName()));
////                    sqlString.append(StringUtils.format(" OR {}.user_id = {} ", userAlias, user.getUserId()));
//                }
//                else
//                {
//                    // 数据权限为仅本人且没有userAlias别名不查询任何数据
//                    sqlString.append(" OR 1=0 ");
//                }
//            }
//        }
//
//        if (StringUtils.isNotBlank(sqlString.toString()))
//        {
//            Object params = joinPoint.getArgs()[0];
//            if (StringUtils.isNotNull(params) && params instanceof BaseEntity)
//            {
//                BaseEntity baseEntity = (BaseEntity) params;
//                baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
//            }
//        }
    }




    /**
     * 是否存在注解，如果存在就获取
     */
    private FieldScope getAnnotationLog(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(FieldScope.class);
        }
        return null;
    }

}
