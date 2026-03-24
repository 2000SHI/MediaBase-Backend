package com.example.media_base;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "test user");
        String token = JWT.create()
                .withClaim("user", claims)  // payload
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .sign(Algorithm.HMAC256("test secret"));
        System.out.println(token);
    }

//    @Test
//    public void testParse() {
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
//                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6InRlc3QgdXNlciJ9LCJleHAiOjE3Njc4MDY4NDB9" +
//                ".K00HC5PcpCw5-WerSsgb5ZGA0alJtfdiC_os2Bn8lJU";
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("test secret")).build();
//        DecodedJWT decodedJWT = verifier.verify(token);
//        Map<String, Claim> claims = decodedJWT.getClaims();
//        System.out.println(claims.get("user"));
//    }
}
