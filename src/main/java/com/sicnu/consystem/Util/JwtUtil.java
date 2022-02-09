package com.sicnu.consystem.Util;

import com.sicnu.consystem.Pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtUtil
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/30 21:35
 * @Version v1.0
 */
@Component
public class JwtUtil {

    private String secret="tangliyi123fddsafasgkahgjdhgkjfhago9gjoasjglkkdsanglkjnfadlkgjnlfkangl;afdngafdng;";

    private long expreation=60480000;

    public String generateToken(String username){
        Map<String, Object> claims=new HashMap<>();
        JwtBuilder jwtBuilder= Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,this.secret)
                .setExpiration(new Date(new Date().getTime()+this.expreation))
                .claim("r","y");
        return jwtBuilder.compact();
    }

    public String getuserfromtoken(String token){
        Claims claims=Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

