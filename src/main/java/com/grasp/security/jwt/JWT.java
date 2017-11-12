package com.grasp.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWT {

    @Value("${api.secret}")
    private static String apiSecret;

    private static Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(apiSecret),
            SignatureAlgorithm.HS512.getJcaName());

    public static String createJwt(String id, String issuer, String subject, long expiration) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                                 .setId(id)
                                 .setIssuedAt(now)
                                 .setSubject(subject)
                                 .setIssuer(issuer)
                                 .signWith(SignatureAlgorithm.HS512, signingKey);

        // check if it's been specified
        if (expiration >= 0) {
            long expirationMillis = nowMillis + expiration;
            Date expirationDate = new Date(expirationMillis);
            builder.setExpiration(expirationDate);
        }

        return builder.compact();
    }


    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                   .setSigningKey(signingKey)
                   .parseClaimsJws(jwt)
                   .getBody();
    }
}
