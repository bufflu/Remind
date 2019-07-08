package com.frog.RemindQing.test;

import com.frog.RemindQing.service.LoginService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/6/28 8:41
 *
 * @author guoxinlu
 */
@Controller
public class Test {

    @RequestMapping("/toLogin2")
    public String lgoin(){
        return "login";
    }

    @Autowired
    ApplicationContext context;

    @RequestMapping("/bean")
    public void bean() {
        LoginService bean = context.getBean(LoginService.class);
        Class<? extends LoginService> aClass = bean.getClass();
        System.out.println(aClass);
    }
}
