package com.lingzhi.crm.framework.aspectj;

import com.lingzhi.crm.common.utils.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis自定义拦截器
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,RowBounds.class,ResultHandler.class,CacheKey.class,BoundSql.class})
        ,@Signature(type = Executor.class,method = "query",args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
@Component
public class MybatisInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(MybatisInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            Object[] args = invocation.getArgs();
            MappedStatement mappedStatement = (MappedStatement) args[0];
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            Object parameter = args[1];
            RowBounds rowBounds = (RowBounds) args[2];
            ResultHandler resultHandler = (ResultHandler) args[3];
            Executor executor = (Executor) invocation.getTarget();
            CacheKey cacheKey;
            BoundSql boundSql;
            if(args.length == 4){
                boundSql = mappedStatement.getBoundSql(parameter);
            } else {
                boundSql = (BoundSql) args[5];
            }
            String originSql = boundSql.getSql();
            log.info("mybatis拦截器----原始sql:{}",originSql);
            cacheKey = args.length == 4 ? executor.createCacheKey(mappedStatement, parameter, rowBounds, boundSql) : (CacheKey) args[4];
            if (SqlCommandType.SELECT == sqlCommandType) {
                //sql语句处理过滤字段信息
                String changedSql = filterFieldScope(parameter,originSql);
                BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(),changedSql,boundSql.getParameterMappings(),parameter);
                return executor.query(mappedStatement,parameter,rowBounds,resultHandler,cacheKey,newBoundSql);
            }
        } catch (Exception e) {
            log.info("mybatis拦截器处理异常:{}",e.getMessage());
        }
        return invocation.proceed();
    }

    /**
     * sql语句处理过滤字段信息
     * @param parameter
     * @param originSql
     * @return
     */
    private String filterFieldScope(Object parameter,String originSql){
        try {
            if(parameter instanceof Map && ((Map<?, ?>) parameter).containsKey("params")){
                Map<String,Object> params = (Map<String,Object>)((Map<String,Object>)parameter).get("params");
                log.info("mybatis拦截过滤字段---入参:{}",params);
                if(params != null && params.containsKey(FieldScopeAspect.FIELD_SCOPE)){
                    List<String> filterFieldNameList = (List<String>)params.get(FieldScopeAspect.FIELD_SCOPE);
                    log.info("mybatis拦截过滤字段---需过滤字段列表:{}",filterFieldNameList);
                    if(filterFieldNameList != null && filterFieldNameList.size() > 0){
                        //查询出sql语句中select和from的位置
                        int startIndex = StringUtils.findFirstIndexOf(originSql,"SELECT","select");
                        int endIndex = StringUtils.findFirstIndexOf(originSql,"from","FROM");
                        if(startIndex >= 0 && endIndex >= 0){
                            //需处理的select返回的字段信息
                            final String[] partSql = {originSql.substring(startIndex, endIndex)};
                            filterFieldNameList.forEach(fieldName -> {
                                //将需过滤的字段替换为null
                                if(partSql[0].contains(fieldName)){
                                    partSql[0] = partSql[0].replace(fieldName,"null as " + fieldName);
                                }
                            });
                            //拼接出过滤完的sql语句
                            String changedSql = partSql[0] + originSql.substring(endIndex) ;
                            log.info("mybatis拦截过滤字段---处理完成sql:{}",changedSql);
                            return changedSql;
                        }
                    }
                }
            }
        }catch (Exception e){
            log.error("sql语句处理过滤字段异常:{}",e.getMessage());
        }
        return originSql;
    }

//    private void setBaseInfoByReflect(Object parameter, String methods) {
//        try {
//            if (parameter instanceof Map) {
//                Map<String, Object> params = (Map<String, Object>) parameter;
//                for (String key : params.keySet()) {
//                    if ("list".equals(key)) {
//                        List<Object> list = (List<Object>) params.get("list");
//                        // 批量执行 (没有此方法则跳过赋值,继续执行sql)
//                        list.forEach(t -> ReflectUtils.invokeMethodByName(t, methods, new Object[0]));
//                    } else {
//                        // 多参数 (没有此方法则跳过赋值,继续执行sql)
//                        ReflectUtils.invokeMethodByName(params.get(key), methods, new Object[0]);
//                    }
//                }
//            } else {
//                // 单参数 (没有此方法则跳过赋值,继续执行sql)
//                ReflectUtils.invokeMethodByName(parameter, methods, new Object[0]);
//            }
//        } catch (Exception e) {
//        }
//    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}