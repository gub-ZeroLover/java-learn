package com.gub;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen(){

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", 1);
        claims.put("username", "gub");

        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .sign(Algorithm.HMAC256("gub"));

        System.out.println(token);



    }
}
