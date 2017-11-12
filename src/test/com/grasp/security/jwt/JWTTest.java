package com.grasp.security.jwt;

import io.jsonwebtoken.Claims;
import org.junit.Test;

import static org.junit.Assert.*;

public class JWTTest {

    @Test
    public void createJwt() throws Exception {
        String token = JWT.createJwt("id", "issuer", "subject", 100000);
        Claims claims = JWT.parseJwt(token);
    }

    @Test
    public void parseJwt() throws Exception {
    }

}