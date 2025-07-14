package com.blog.filter;

import com.aliyun.oss.common.utils.StringUtils;
import com.blog.build.RequestManagerBuilder;
import com.blog.build.RequestManagers;
import com.blog.context.RequestContext;
import com.blog.exception.BusinessException;
import com.blog.exception.BusinessMsgEnum;
import com.blog.exception.ResponseResult;
import com.blog.properties.JwtProperties;
import com.blog.utils.JwtUtil;
import com.blog.vo.RequestContextManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter implements Filter {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String jwtToken = getJwtFromRequest(request);
        log.info("（解析后）本次请求的token为：" + jwtToken);

        String requestURI = request.getRequestURI();
        // 判断请求路径是否是登录路径，假设 "/api/auth/login" 是登录路径
        if (requestURI.contains("/api/auth/login") || requestURI.contains("/api/auth/register")) {
            filterChain.doFilter(servletRequest, servletResponse);  // 如果是登录路径，直接放行
            return;
        }

        if (!StringUtils.isNullOrEmpty(jwtToken) && JwtUtil.validateToken(jwtProperties.getSecretKey(),jwtToken)) {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), jwtToken);
            RequestManagerBuilder requestManagerBuilder = RequestManagers.builder().buildUser(claims);
            RequestContextManager requestContextManager = requestManagerBuilder.getRequestContextManager();
            RequestContext.setRequestInfo(requestContextManager);
        }else {
            ResponseResult responseResult = ResponseResult.error("401","NOT LOGIN","请您先登录");
            // 将响应体转换为 JSON
            String jsonResponse = new ObjectMapper().writeValueAsString(responseResult);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(jsonResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        log.info("本次请求的请求头中的Authorization为：" + bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
