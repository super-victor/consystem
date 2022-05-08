package com.sicnu.consystem.config;

import com.sicnu.consystem.Intercep.MyInterceptor;
//import com.sicnu.consystem.Intercep.loginInterceptor;
//import com.sicnu.consystem.Intercep.WebSocketInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

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

//    @Resource
//    WebSocketInterceptor webSocketInterceptor;


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static/image/**").addResourceLocations("classpath:/static/image/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/*")
                .excludePathPatterns("/login")
        .excludePathPatterns("");

//        registry.addInterceptor(webSocketInterceptor).addPathPatterns();
//        registry.addInterceptor(new loginInterceptor()).addPathPatterns("/login");
    }
}

