package Servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Cookes
{
    public String getCookieValue(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("U_ID".equals(ck.getName())) {
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    public String updateCookieTime(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("U_ID".equals(ck.getName())) {
                    ck.setMaxAge(120);
                }
            }
        }
        return null;
    }

}