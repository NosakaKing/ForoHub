package com.forohub.forohub.infra.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie createAccessTokenCookie(String token, int maxAgeSeconds) {
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeSeconds);
        return cookie;
    }

    public static Cookie clearAccessTokenCookie() {
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        return cookie;
    }

}
