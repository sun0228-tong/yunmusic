package com.wangyi.common.util;

import com.wangyi.common.config.SystemConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * @program: yunmusic
 * @description: 生成和解析JWT格式的数据
 * @author: suntong
 */
public class JwtUtil {
    //生成JWT格式的令牌
    public static String createJWT(String msg) {
        //1、指定签名的加密方式
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        //2、创建jwt对象
        JwtBuilder jwtBuilder = Jwts.builder();
        //3、设置令牌信息
        jwtBuilder.setIssuedAt(new Date()); //起始时间
        jwtBuilder.setExpiration(TimeUtil.getTime(600)); //结束时间 10分钟后
        jwtBuilder.setSubject(msg);

        //4、生成签名
        jwtBuilder.signWith(algorithm,createKey());
        //5、生成JWT令牌
        return jwtBuilder.compact();
    }

    //生成签名
    private static Key createKey() {
        byte[] data = SystemConfig.TOKEN_KEY.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(data, 0, data.length, "AES");
        return secretKeySpec;
    }

    //解析
    public static String parseJWT(String token) {
        //解析字符串 令牌 jwt格式
        Claims claims = Jwts.parser().setSigningKey(createKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
