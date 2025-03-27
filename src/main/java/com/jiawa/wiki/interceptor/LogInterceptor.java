package com.jiawa.wiki.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 记录请求开始时间
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        // 记录请求信息
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        logger.info("LogInterceptor: Request URL: {}, Method: {}", requestURI, method);
        logger.info("Request Parameters: {}", getRequestParams(request));

        return true; // 继续执行下一个拦截器或目标方法
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 获取请求开始时间
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        // 记录响应信息
        int status = response.getStatus();
        logger.info("Response Status: {}", status);
        logger.info("Request completed in {} ms", (endTime - startTime));
    }

    /**
     * 获取请求参数并格式化为字符串
     */
    private String getRequestParams(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        request.getParameterMap().forEach((key, values) -> {
            params.append(key).append("=").append(String.join(",", values)).append("&");
        });

        // 删除最后一个多余的 "&"
        if (params.length() > 0) {
            params.deleteCharAt(params.length() - 1);
        }

        return params.toString();
    }
}
