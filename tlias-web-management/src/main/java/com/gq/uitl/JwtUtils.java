package com.gq.uitl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "itheima";
    private static Long expire = 43200000L;

    public static String generateJwt(Map<String,Object> claims){

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,signKey) //签名算法
                .addClaims(claims) //自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + expire)) //设置有效期为1h
                .compact();
        System.out.println("生成的jwt令牌为："+jwt);
        return jwt;
    }

    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
