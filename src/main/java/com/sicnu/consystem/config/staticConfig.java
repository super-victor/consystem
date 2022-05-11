package com.sicnu.consystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName staticConfig
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/15 19:56
 * @Version v1.0
 */
@Configuration
public class staticConfig implements WebMvcConfigurer {


    @Value("${accessFile.resourceHandler}")
    private String resourceHandler; //匹配url 中的资源映射

    @Value("${accessFile.location}")
    private String location; //上传文件保存的本地目录


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/image/");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/file/");

        registry.addResourceHandler("/download/**").addResourceLocations("file:D:/myfile");
    }
}

