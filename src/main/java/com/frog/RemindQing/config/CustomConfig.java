package com.frog.RemindQing.config;

import com.frog.RemindQing.job.DefaultPushHandle;
import com.frog.RemindQing.job.PushListHandle;
import com.frog.RemindQing.mapper.RemindJobMapper;
import com.frog.RemindQing.mapper.UserMapper;
import com.frog.RemindQing.service.DefaultLoginServie;
import com.frog.RemindQing.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: CustomConfig
 * Description: desc
 * Date: 2019/7/8 11:44
 *
 * @author guoxinlu
 */
@Configuration
public class CustomConfig {

    @Bean
    public LoginService getLoginService(UserMapper userMapper) {
        return new DefaultLoginServie(userMapper);
    }

    @Bean
    public PushListHandle getPushHandle(RemindJobMapper remindJobMapper) {
        return new DefaultPushHandle(remindJobMapper);
    }
}
