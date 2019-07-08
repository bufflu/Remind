package com.frog.RemindQing.common;

import com.frog.RemindQing.utils.SessionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ClassName: RemindLogAOP
 * Description: The aop print log that method's name, method's param and return
 * Date: 2019/7/2 11:49
 *
 * @author guoxinlu
 */
@Aspect
@Order(1)
@Configuration
public class RemindLogAOP {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 切点
    private final static String POINTCUT_LOG_REST   = "execution (* com.frog.RemindQing.controller.*.*(..))";
    private final static String POINTCUT_LOG_BIZ    = "execution (* com.frog.RemindQing.service.*.*(..))";
    private final static String POINTCUT_LOG = POINTCUT_LOG_REST + " || " + POINTCUT_LOG_BIZ;


    // 环绕通知
    @Around(POINTCUT_LOG)
    public Object logAround(ProceedingJoinPoint joinPoint) {
        // 获取用户名
        String username = (String) SessionUtil.getSession(Constant.USERNAME);
        // 获取切点参数
        Object[] args = joinPoint.getArgs();
        // 获取方法全限定名
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "_" + joinPoint.getSignature().getName();
        // 打印执行前日志
        printBeforeLog(username, methodName, args);
        // 执行时间起
        long start = System.currentTimeMillis();
        try {
            // 执行方法
            Object proceed = joinPoint.proceed();
            // 执行时间止
            long end = System.currentTimeMillis();
            // 打印执行后日志
            printAfterLog(username, methodName, args, proceed, end - start);
            return proceed;
        } catch (Throwable e) {
            // 处理异常
            printErrorLog(username, methodName, args, e);
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        }
    }


    private void printErrorLog(String name, String methodName, Object[] args, Throwable e) {
        if (logger.isInfoEnabled()) {
            logger.info("[{}]-[{}] 方法执行异常，参数[{}]，异常信息[{}]",
                    name,
                    methodName,
                    argsToParamStr(args),
                    e);
        }
    }

    private void printAfterLog(String name, String methodName, Object[] args, Object proceed, long l) {
        if (logger.isInfoEnabled()) {
            logger.info("[{}]-[{}] 方法执行成功，参数[{}]，执行时间[{}ms]，返回值[{}]",
                    name,
                    methodName,
                    argsToParamStr(args),
                    l,
                    proceedToStr(proceed));
        }
    }

    private void printBeforeLog(String name, String methodName, Object[] args) {
        if (logger.isInfoEnabled()) {
            logger.info("[{}]-[{}] 方法请求成功，参数[{}]", name, methodName, argsToParamStr(args));
        }
    }

    private String argsToParamStr(Object... args) {
        return Stream.of(args)
                .map(o -> {
                    if (o == null) {
                        return "null";
                    }
                    if (o.getClass().isPrimitive()) {
                        // 基本类型输出值
                        return o.toString();
                    } else {
                        // 不是基本类型
                        if (o.toString().length() > 100) {
                            return o.getClass().getName();
                        } else {
                            return o.toString();
                        }
                    }
                })
                .collect(Collectors.joining(", "));
    }

    private String proceedToStr(Object proceed) {
        return argsToParamStr(proceed);
    }
}
