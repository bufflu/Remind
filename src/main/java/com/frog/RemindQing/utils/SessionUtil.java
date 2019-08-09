package com.frog.RemindQing.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: SessionUtil
 * Description: desc
 * Date: 2019/7/8 9:11
 *
 * @author guoxinlu
 */
public class SessionUtil {

    public static Object getSession(String key) {
        Object value = null;
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                value = ((ServletRequestAttributes) requestAttributes).getRequest().getSession().getAttribute(key);
            }
        } catch (Exception e) {}
        return value;
    }

    public static HttpServletRequest getHttpServletRequest() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                return  ((ServletRequestAttributes) requestAttributes).getRequest();
            }
        } catch (Exception e) {}
        return null;
    }
}
