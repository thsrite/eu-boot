package com.eu.datasource.aspectj;

import com.eu.datasource.annotation.DataSource;
import com.eu.datasource.datasource.DynamicDataSourceContextHolder;
import com.eu.datasource.enums.DataSourceType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 多数据源处理
 *
 * @author jiangxd
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    //    @Pointcut("@annotation(com.eu.core.annotation.DataSource)"
//            + "|| @within(com.eu.core.annotation.DataSource)")
    @Pointcut("execution(public * com.eu.*.controller..*.*(..))")
    public void dsPointCut() {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = getDataSource(point);

        if (null != dataSource) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        } else {
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.MASTER.name());
        }

        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DataSource dataSource = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
        if (Objects.nonNull(dataSource)) {
            return dataSource;
        }

        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
    }
}
