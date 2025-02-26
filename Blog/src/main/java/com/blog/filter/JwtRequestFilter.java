package com.blog.filter;

import com.aliyun.oss.common.utils.StringUtils;
import com.blog.build.RequestManagerBuilder;
import com.blog.build.RequestManagers;
import com.blog.context.RequestContext;
import com.blog.properties.JwtProperties;
import com.blog.utils.JwtUtil;
import com.blog.vo.RequestContextManager;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter implements Filter {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwtToken = getJwtFromRequest(request);

        String requestURI = request.getRequestURI();
        // 判断请求路径是否是登录路径，假设 "/api/auth/login" 是登录路径
        if (requestURI.contains("/api/auth/login")) {
            filterChain.doFilter(servletRequest, servletResponse);  // 如果是登录路径，直接放行
            return;
        }

        if (!StringUtils.isNullOrEmpty(jwtToken) && JwtUtil.validateToken(jwtProperties.getSecretKey(),jwtToken)) {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), jwtToken);
            RequestManagerBuilder requestManagerBuilder = RequestManagers.builder().buildUser(claims);
            RequestContextManager requestContextManager = requestManagerBuilder.getRequestContextManager();
            RequestContext.setRequestInfo(requestContextManager);
        }else {
            // 校验失败，返回401 Unauthorized 错误
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401
            response.getWriter().write("Unauthorized - Invalid or Expired Token");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
