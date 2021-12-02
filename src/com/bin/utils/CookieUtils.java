package com.bin.utils;

import javax.servlet.http.Cookie;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.utils
 * @ClassName: CookieUtils
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/16 10:53
 */
public class CookieUtils {
    public static Cookie findCookie(String name, Cookie[] cookies) {
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}
