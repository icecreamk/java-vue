//package com.jiawa.wiki.config;
//
//
//import com.jiawa.wiki.interceptor.LogInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    private final LogInterceptor logInterceptor;
//
//    public WebConfig(LogInterceptor logInterceptor) {
//        this.logInterceptor = logInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册拦截器并指定拦截路径
//        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/**") // 拦截所有路径
//                .excludePathPatterns("/static/**", "/error", "/login"); // 排除静态资源和错误页面
//    }
//}
