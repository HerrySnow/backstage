package com.baojia.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/*@Component*/
@Configuration
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        //获取请求路径
        String path = request.getRequestURI();
        if (path.indexOf("/user/login") > -1||path.indexOf("/swagger-ui.html")>-1||path.indexOf("/swagger-resources")>-1
                ||path.indexOf("/v2/api-docs")>-1) {
            chain.doFilter(req, res);
        }else {
            //客户端将token封装在请求头中，格式为（Bearer后加空格）：Authorization：Bearer +token
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header.");
            }

            //去除Bearer 后部分
            final String token = authHeader.substring(7);

            try {
                //解密token，拿到里面的对象claims
                String name = Jwts.parser().setSigningKey("secretkey")
                        .parseClaimsJws(token).getBody().getSubject();
                //将对象传递给下一个请求
                request.setAttribute("name", name);
            } catch (final SignatureException e) {
                throw new ServletException("Invalid token.");
            }

            chain.doFilter(req, res);
        }
    }

}