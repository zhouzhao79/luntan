package com.example.luntan.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class indexConfig implements WebMvcConfigurer {
    @Autowired
    IndexSessionInterceptor indexSessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexSessionInterceptor).addPathPatterns("/");
    }
}
