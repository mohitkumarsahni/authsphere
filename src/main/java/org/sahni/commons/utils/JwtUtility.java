package org.sahni.commons.utils;

import io.smallrye.jwt.build.Jwt;

import java.time.Instant;

public class JwtUtility {

    private static final String ISSUER = "authsphere-api";

    public static String createJWT(String username) {
        return Jwt.issuer(ISSUER)
                .upn(username)
                .expiresAt(Instant.now().plusSeconds(60))
                .sign();
    }

//    public Optional<JsonWebToken> validateJWT(String token) {
//        try {
//            JsonWebToken jwt = Jwt.claim(token);
//            return Optional.of(jwt);
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }
}

