package com.sicnu.consystem.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @ClassName FileConfig
 * @Description
 * @Author pan
 * @LastChangeDate 2022/5/8 16:16
 * @Version v1.0
 */
@Configuration
public class FileConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //指定文件代销
        factory.setMaxFileSize(DataSize.ofBytes(10485760*1024));
        /// 设定上传文件大小
        factory.setMaxRequestSize(DataSize.ofBytes(10485760*1024));
        return factory.createMultipartConfig();
    }
}

