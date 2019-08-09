package com.frog.RemindQing.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.frog.RemindQing.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: LoginInterceptor
 * Description: this is login interceptor, check user's token
 * Date: 2019/6/27 10:35
 *
 * @author guoxinlu
 */
@Component("defaultLoginInterceptor")
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginServie;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String)request.getSession().getAttribute(Constant.USERNAME);
        Integer token = (Integer)request.getSession().getAttribute(Constant.TOKEN);
        String userType = (String)request.getSession().getAttribute(Constant.USERTYPE);
        if (StringUtils.isEmpty(username) || token == null) {
            return false;
        } else {
            return loginServie.checkLoginStatus(username, token, userType);
        }
    }
}
