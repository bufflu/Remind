package com.frog.RemindQing.service;

import com.frog.RemindQing.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: LoginService
 * Description: desc
 * Date: 2019/7/8 10:35
 *
 * @author guoxinlu
 */
public interface LoginService {

    void login(User user, HttpServletRequest request);

    boolean checkLoginStatus(Object... objects);

    User getSessionUser(HttpServletRequest request);

    void loginAuth(Object data);
}
