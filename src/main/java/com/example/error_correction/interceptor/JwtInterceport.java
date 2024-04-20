package com.example.error_correction.interceptor;


import com.example.error_correction.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceport implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  Object handler) throws Exception {
        String token = request.getHeader("token");
        Claims claims = JwtUtil.parseToken(token);
        if(claims.isEmpty()){
            return false;
        }
        return true;
    }
}
