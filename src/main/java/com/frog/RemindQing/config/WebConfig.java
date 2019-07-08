package com.frog.RemindQing.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: WebConfig
 * Description: configuration
 * Date: 2019/6/26 14:46
 *
 * @author guoxinlu
 */
@EnableTransactionManagement
@MapperScan("com.frog.RemindQing.mapper")
@Configuration
@EnableScheduling
public class WebConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
