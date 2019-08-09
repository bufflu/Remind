package com.frog.RemindQing.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: RemindWebMvcConfigurerAdapter
 * Description: desc
 * Date: 2019/6/27 10:52
 *
 * @author guoxinlu
 */
@Configuration
public class RemindWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    @Qualifier("defaultLoginInterceptor")
    private HandlerInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/start/login");
        excludePathList.add("/user/register/*");
        excludePathList.add("/fonts/*");
        excludePathList.add("/css/*");
        excludePathList.add("/js/*");
        excludePathList.add("/ico/*");
        excludePathList.add("/**/*.html");
        excludePathList.add("/oauth/**");
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(excludePathList);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
    }

}
