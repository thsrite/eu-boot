package com.eu.core.aspect;

import com.eu.core.exception.GlobalException;
import com.eu.core.thread.CurrentUser;
import com.eu.core.utils.ServletUtils;
import com.eu.core.utils.ip.IpUtils;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class GlobalLogAspect {

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * com.eu.*.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {

    }

    /**
     * 在切点之后织入
     */
    @After("webLog()")
    public void doAfter() {
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 获取当前线程名称
        String currentThreadName = Thread.currentThread().getName();

        Long userId = 0L;
        String userName = "";
        try {
            userId = CurrentUser.getId();
            userName = CurrentUser.getUsername();
        } catch (Exception ignored) {

        }

        StringBuilder sbf = new StringBuilder("\n");
        // 打印请求相关参数
        sbf.append("========================================== Start ==========================================").append("\n");
        sbf.append("Current Thread Name     :").append(currentThreadName).append("\n");

        // 打印调用 controller 的全路径以及执行方法
        String methodName = proceedingJoinPoint.getSignature().getName();

        // 打印调用 controller 的全路径以及执行方法
        sbf.append("Class Method            :").append(proceedingJoinPoint.getSignature().getDeclaringTypeName()).append(".").append(methodName).append("\n");

        try {
            Class[] parameterTypes = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = proceedingJoinPoint.getTarget().getClass().getMethod(methodName, parameterTypes);
            // 获取请求方法的swagger注解
            ApiOperation annotation = method.getAnnotation(ApiOperation.class);
            if (null != annotation) {
                // 请求方法中文名称
                sbf.append("Method Name             :").append(annotation.value()).append("\n");
            }
        } catch (NoSuchMethodException | SecurityException ignored) {
        }

        // 打印请求的 userNum
        sbf.append("CurrentUser             :").append(userId).append(":").append(userName).append("\n");
        // 请求ip
        String remoteAddr = IpUtils.getIpAddr(ServletUtils.getRequest());
        sbf.append("Remote Addr             :").append(remoteAddr).append("\n");
        // 打印请求入参
        String requestArgs = this.printRequestArgs(proceedingJoinPoint);
        sbf.append("Request Args            :").append(requestArgs).append("\n");

        try {
            Object result = proceedingJoinPoint.proceed();
            // 打印出参
            sbf.append("Response Args           :").append(new Gson().toJson(result)).append("\n");
            // 执行耗时
            sbf.append("Time-Consuming          :").append(System.currentTimeMillis() - startTime).append("ms").append("\n");
            sbf.append("=========================================== End ===========================================");

            log.info(sbf.toString());
            return result;
        } catch (GlobalException e) {
            // 打印出参
            sbf.append("Response Args           :").append("EXCEPTION " + e.getMessage()).append("\n");
            // 执行耗时
            sbf.append("Time-Consuming          :").append(System.currentTimeMillis() - startTime).append("ms").append("\n");
            sbf.append("=========================================== End ===========================================");
            log.info(sbf.toString());
            throw e;
        }

    }

    /**
     * 打印请求参数
     *
     * @param joinPoint
     */
    public String printRequestArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); // 参数值
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < argNames.length; i++) {
            sb.append(argNames[i]).append("=").append(args[i]);
            if (i + 1 < argNames.length) {
                sb.append("&");
            }
        }

        return sb.toString();
    }

}