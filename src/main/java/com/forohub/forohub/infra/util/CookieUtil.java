package com.forohub.forohub.infra.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie createRefreshTokenCookie(String token, int maxAgeSeconds) {
        Cookie cookie = new Cookie("refreshToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeSeconds);
        return cookie;
    }

    public static Cookie clearRefreshTokenCookie() {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        return cookie;
    }
}
