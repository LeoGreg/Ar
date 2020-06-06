package am.basic.jdbcStart.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static am.basic.jdbcStart.util.Constants.PagesLinks.INDEX_PAGE;

public class CookieTestServlet extends HttpServlet {


    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookies != null && cookies.length > 0) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        Cookie cookie= new Cookie("myCookie","AAGG989900231eeEE33");
        cookie.setMaxAge(5);
        response.addCookie(cookie);
        response.sendRedirect(INDEX_PAGE);

    }
}
