package com.jiawa.wiki.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LogFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 记录请求信息
        long startTime = System.currentTimeMillis();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        logger.info("Request URL: {}, Method: {}", requestURI, method);
        logger.info("Request Parameters: {}", getRequestParams(request));

        // 包装响应对象以捕获响应内容
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        try {
            // 继续执行过滤器链
            filterChain.doFilter(request, responseWrapper);
        } finally {
            // 记录响应信息
            int status = response.getStatus();
            String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);

            logger.info("LoginFilter Response Status: {}", status);
            logger.info("Response Body: {}", responseBody);

            // 将响应内容写回客户端
            responseWrapper.copyBodyToResponse();
        }

        long endTime = System.currentTimeMillis();
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