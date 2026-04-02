package com.example.media_base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/api/user/login", "/api/user/register")
                .excludePathPatterns("/api/test")
                .excludePathPatterns("/api/media/list", "/api/media/detail", "/api/media/people")
                .excludePathPatterns("/api/search")
                .excludePathPatterns("/api/person/detail", "/api/person/media");
    }
}
