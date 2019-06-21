package app.servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFunction {

    public static String a;



    public static String getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        String cookieName = name;
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie = c;
                    return cookie.getValue();

                }
            }
        }
        return "";
    }


    public static void clearCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                cookie.setPath("/TimeTracking_war_exploded");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }

    }
}
