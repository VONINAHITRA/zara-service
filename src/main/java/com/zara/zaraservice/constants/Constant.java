package com.zara.zaraservice.constants;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class Constant {
    //Auth
    public static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7 jours en millisecondes
    public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("aVerySecretKeyThatIsAtLeast32Characters!".getBytes());
    public static final long ACCESS_TOKEN_EXPIRATION = 604800000;
    public static final String REDIRECTION_URL_TO_FRONT = "http://localhost:4200/auth?confirmed=true";

}
