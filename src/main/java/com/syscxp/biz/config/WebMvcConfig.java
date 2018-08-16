package com.syscxp.biz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-10.
 * @Description: .
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("http://localhost:3000")
                //是否允许证书
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods(CorsConfiguration.ALL)
                //设置允许的请求头
                .allowedHeaders(CorsConfiguration.ALL)
                //跨域允许时间
                .maxAge(10000);
    }
}
