package com.example.error_correction.utils;

import io.jsonwebtoken.Claims;
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
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
