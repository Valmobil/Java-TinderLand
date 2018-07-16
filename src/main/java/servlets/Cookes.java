package servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class Cookes {
    private int cookieTimeOut = 120;

    String getCookieValue(HttpServletRequest req, String cookieName) {

        //Check if cookie value exists
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

    String updateCookieTime(HttpServletRequest req, HttpServletResponse resp, String cookieName) {

        //Update cookie time
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("U_ID".equals(ck.getName())) {
                    ck.setMaxAge(cookieTimeOut);
                    resp.addCookie(ck);
                }
            }
        }
        return null;
    }

    void setCokies(HttpServletResponse resp, String u_id, String cookieValue, int i) {

        //Create new cookie
        Cookie cookie = new Cookie("U_ID", cookieValue);
        cookie.setMaxAge(cookieTimeOut);
        resp.addCookie(cookie);
    }
}