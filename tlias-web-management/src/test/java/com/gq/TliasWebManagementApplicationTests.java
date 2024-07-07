package com.gq;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    /*生成jwt编码*/
    @Test
    void testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima") //签名算法
                .setClaims(claims) //自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置有效期为1h
                .compact();
        System.out.println("生成的jwt令牌为："+jwt);
    }
    /*解析jwt编码*/
    @Test
    void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5p2O57qiIiwiaWQiOjIwLCJ1c2VybmFtZSI6ImxpaG9uZyIsImV4cCI6MTcyMDI5NTQzMH0.LDGFUUY3KUV6vdk7UnWNbY8B9b7xuO2z6BFQx1GgqAw")
                .getBody();
        System.out.println("解析的数据为："+claims);
    }
}
