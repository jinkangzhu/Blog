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
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
public class JwtRequestFilter implements Filter {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwtToken = getJwtFromRequest(request);
        if (!StringUtils.isNullOrEmpty(jwtToken) && JwtUtil.validateToken(jwtProperties.getSecretKey(),jwtToken)) {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), jwtToken);
            RequestManagerBuilder requestManagerBuilder = RequestManagers.builder().buildUser(claims);
            RequestContextManager requestContextManager = requestManagerBuilder.getRequestContextManager();
            RequestContext.setRequestInfo(requestContextManager);
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
