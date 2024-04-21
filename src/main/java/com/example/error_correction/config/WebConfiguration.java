package com.example.error_correction.config;

import com.example.error_correction.interceptor.JwtInterceport;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceport())
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/login","/reset_pd","/register");  // 排除不需要Token验证的路径
    }
}
