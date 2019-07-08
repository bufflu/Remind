package com.frog.RemindQing.controller;

import com.frog.RemindQing.common.Result;
import com.frog.RemindQing.entity.User;
import com.frog.RemindQing.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: LoginController
 * Description: desc
 * Date: 2019/6/27 9:34
 *
 * @author guoxinlu
 */
@RestController
@RequestMapping("/start")
public class LoginController {

    @Autowired
    private LoginService loginServie;

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request) {
        loginServie.login(user, request);
        return Result.ok();
    }

}
