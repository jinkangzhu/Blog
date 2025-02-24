package com.blog.filter;

import com.aliyun.oss.common.utils.StringUtils;
import com.blog.build.RequestManagerBuilder;
import com.blog.build.RequestManagers;
import com.blog.constant.JwtClaimsConstant;
import com.blog.context.RequestContext;
import com.blog.properties.JwtProperties;
import com.blog.utils.JwtUtil;
import com.blog.vo.RequestContextManager;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtRequestFilter implements Filter {

    @Autowired
    private JwtProperties jwtProperties;

    //private String excludeUrls;  // 存储排除的路径

//    public void setJwtProperties(JwtProperties jwtProperties) {
//        this.jwtProperties = jwtProperties;
//    }

    // 初始化方法，用于获取配置的初始化参数
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // 获取初始化参数，排除的路径
//        this.excludeUrls = filterConfig.getInitParameter("excludeUrls");
//    }

    /**
     * 过滤出合法用户（有效token）
     * @param servletRequest 请求
     * @param servletResponse 返回
     * @param filterChain 过滤器链
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        // 打印请求的路径
        System.out.println("Request URI: " + requestURI);

//        // 获取初始化参数中配置的排除路径
//        if (excludeUrls != null && Arrays.asList(excludeUrls.split(",")).contains(requestURI)) {
//            filterChain.doFilter(servletRequest, servletResponse);  // 直接放行，不进行Token校验
//            return;
//        }

        String jwtToken = getJwtFromRequest(request);
        System.out.println("JWT Token: " + jwtToken);
        // 校验是否有效，有效则把信息存入ThreadLocal，无效则抛异常
        if (!StringUtils.isNullOrEmpty(jwtToken) && JwtUtil.validateToken(jwtProperties.getSecretKey(),jwtToken)) {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), jwtToken);
            // RequestManagerBuilder requestManagerBuilder = RequestManagers.builder().buildUser(claims);
            // RequestContextManager requestContextManager = requestManagerBuilder.getRequestContextManager();
            RequestContext.setRequestInfo((RequestContextManager)claims.get(JwtClaimsConstant.REQUEST_CONTEXT_MANAGER));
        }else {
            // 返回401或者403错误，详细的错误信息
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);  // 403 Forbidden
            response.getWriter().write("Invalid or missing token");
            System.out.println("Token is invalid or missing.");
            return;  // 中断后续的过滤器链
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        System.out.println("Bearer Token: " + bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
