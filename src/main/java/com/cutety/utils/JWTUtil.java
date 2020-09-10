package com.cutety.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

public class JWTUtil {
    private static final String SIGNATURE = "!s(2_n@TA%1^c*7";
    /**
     * 生成token
     * @param payload 负载因子
     * @param ttl 过期时间
     * @return String token
     */
    public static String createToken(Map<String,String> payload, int ttl) {
        Algorithm algorithm = Algorithm.HMAC256(SIGNATURE);
        JWTCreator.Builder builder = JWT.create();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,ttl);
        builder.sign(algorithm);
        payload.forEach(builder::withClaim);
        return builder.withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

    /**
     * 验证token
     * @param token token
     * @return token对象
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }

    public void invalidateRelatedTokens(HttpServletRequest httpServletRequest) {

    }
}
