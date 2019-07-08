package com.frog.RemindQing.common;

import com.frog.RemindQing.utils.SessionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


/**
 * ClassName: ReturnNameAOP
 * Description: The aop set username in Result.
 * Date: 2019/7/4 9:32
 *
 * @author guoxinlu
 */
@Aspect
@Order(2)
@Configuration
public class ReturnNameAOP {

    private final static String POINTCUT_REST = "execution (* com.frog.RemindQing.controller.*.*(..))";

    @Around(POINTCUT_REST)
    public Object setName(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        if (proceed instanceof Result) {
            String name = (String)SessionUtil.getSession(Constant.USERNAME);
            if (name != null) {
                ((Result) proceed).setUsername(name);
            }
        }
        return proceed;
    }

}
