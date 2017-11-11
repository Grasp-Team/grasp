package com.grasp.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWT {

    // TODO: Load this off of disk so it's not committed lol - terrible security breach - remember to change it when you do that
    private static String apiSecret = "MIIEpAIBAAKCAQEA1PDIzWzIi3Bxw4ZOv/jQwdCMBWEZQ1ZC6X4YPE9SskUMov8o\n" +
            "FPa7cQCY5V87OtGhXmXrciHzii19P/pPVT7OT6AlcINOmdXB028koIgiwQ5jAelz\n" +
            "LNHP/E7ZcLt568w2AHAqKpxr/OEPbi4S0X7IW5WQIjJEZQK5z7Hniux2dFBkyk5s\n" +
            "Y74YuWn+c6FjGPmpQqseWe1eX5AWF4sgdwZjxAuejH762UBJH9kDQ1OD13LPnsmb\n" +
            "1SAm19V05pDk+c6ujzQmeanfytsWm7AZHH/JsLO0DftY1d1KNfbD8J6oWr1fKlfp\n" +
            "W2hgAHaEX/eUlPAa2iL0DS7pZIfs09IBDez7zQIDAQABAoIBAQCRTlGoJFBhHnTz\n" +
            "GIZKZ46EgzvZO94SXh3A2WPOW86xiX7LB1ShoZBc1yx+cNKpG32DPgdBVburkckD\n" +
            "TFqwb/A207BYq7GKNhxSnGghB2S32opjNpt6fR3xLRkh3TygVDSRbfjIJP9ni2iN\n" +
            "JfF/dYVhQZkeTLDxQL/s45h960gVq7sFT0ZIz3kzGUGFUX9mUFkGXKZYARuwjYYy\n" +
            "D8NEbCj8ava4LsLOnksRe6ntNAYX7r8zNnepulsxF1204oidoAl0lT5NZBCvN1nT\n" +
            "jlye7qqhyLdySl8+kR0bs58l74KGLdj1ZTZLL9lLvbROY45juVV6aJXtzIEVeu5s\n" +
            "xgFmJ2cdAoGBAPoSxh9J3hptukqbBu322kow1jr4MWL4bdkHyMJBMC85dVssYq4e\n" +
            "M+5eEjOLR6gemOJ7dBvCaI1sn/urKHdlkA3hXm7HXoV3qftP38+GoRkdtrh6z3Bt\n" +
            "SZ0hEGHLm+QCcJ7B30bLUUX4C/CYkXg30KDvq/4rUIx3oyMtqoTUvVQDAoGBANn8\n" +
            "uKhHTJ/110b/3G1KvoNOgBygp3NQOrHLpSSmjZVav6TZbiiOXnAtySZ771T+SfaP\n" +
            "gm6A/dUJH8j4oys0OFvnHV1T6J5If5xLq2ApI60+6BH65fhUDwJ9jfoW3imjvUH9\n" +
            "T1LtwHF5ohMpujKOOm/ZIYK+4mqhLta8letDlS/vAoGAcVP4vU04fjmj5xOCfG0W\n" +
            "V/Dx5R99IiD7hxNkA8SPFZewe3UN82J0nl27WeiQvLvBBUJ1R+8+enjzt5XFbPZ1\n" +
            "5hRwOzvWth2Yxu9XujCE6WDx4YCmaYjdh3vodKlVKGuCpi+uA+M4HcFfh9Vcd2K0\n" +
            "BXsYMsNmaYH+SH71e+T9OAkCgYBEfu3DKsxAAy7HB4TK7s6YUeVdmXmkeVRII9Zb\n" +
            "gt5ATQilExFTOxEfrLNlHVJh4Zl6GTQB5xRbiYdsTfxvifQfpKqMdd5FRkCa6wgJ\n" +
            "qkOkn3yqka0Lb9ZbkPo42FCswt+oSaFQTJbI0VCFNvpkAl8IYDDwdWyN1uHx5cYx\n" +
            "68WIDwKBgQDyff/3mc038N15qTddKReUIzXm6AMVT10oK5npvYELxuW45EOayJlp\n" +
            "m76v9N4/cE8Dg1CRLrkkUzYodHqNZtJCt8XeQijZ3PfNAfkmcVWa+QAnGLQoUnw2\n" +
            "RkJIShXfbucvHbUrRB9cw2k7qM3qa8nntije4vEDtQ3ivV5r5pimbA==\n";

    public static String createJwt(String id, String issuer, String subject, long expiration) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES512;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiSecret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                                 .setId(id)
                                 .setIssuedAt(now)
                                 .setSubject(subject)
                                 .setIssuer(issuer)
                                 .signWith(signatureAlgorithm, signingKey);

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
                   .setSigningKey(DatatypeConverter.parseBase64Binary(apiSecret))
                   .parseClaimsJwt(jwt)
                   .getBody();
    }
}
