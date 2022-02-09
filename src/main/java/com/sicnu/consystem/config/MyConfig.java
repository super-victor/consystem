package com.sicnu.consystem.config;

import com.sicnu.consystem.Intercep.MyInterceptor;
import com.sicnu.consystem.Intercep.loginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @ClassName Config
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/30 22:32
 * @Version v1.0
 */
@Configuration
public class MyConfig extends WebMvcConfigurationSupport {

    @Resource
    MyInterceptor myInterceptor;

    @Resource
    com.sicnu.consystem.Intercep.loginInterceptor loginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/*").excludePathPatterns("/login");
        registry.addInterceptor(new loginInterceptor()).addPathPatterns("/login");
    }
}

