package com.example.error_correction.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {


    private static long expire = 604800;
    private static String secret = "error_correction";

    public static String generateToken(String username) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire * 1000);
        return Jwts.builder().setHeaderParam("type","JWT").setSubject(username).setIssuedAt(now).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    public static Claims parseToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            // 如果没有抛出异常，表示token解析成功
            // 在这里处理解析成功后的逻辑
            return claims;
        } catch (JwtException e) {
            // 捕获JwtException异常，表示token解析出错
            // 在这里处理token解析出错的情况
            System.out.println("Token解析出错：" + e.getMessage());
            return null;
        }
    }
}
