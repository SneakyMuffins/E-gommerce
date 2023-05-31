package com.egommerce.demo.config;

import com.egommerce.demo.interceptor.AdminOnlyInterceptor;
import com.egommerce.demo.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor;
    private final AdminOnlyInterceptor adminOnlyInterceptor;

    @Autowired
    public WebMvcConfig(AuthorizationInterceptor authorizationInterceptor, AdminOnlyInterceptor adminOnlyInterceptor) {
        this.authorizationInterceptor = authorizationInterceptor;
        this.adminOnlyInterceptor = adminOnlyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
        registry.addInterceptor(adminOnlyInterceptor);
    }
}
