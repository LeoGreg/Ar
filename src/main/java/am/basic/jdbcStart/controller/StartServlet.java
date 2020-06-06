package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.model.exceptions.InternalServerError;
import am.basic.jdbcStart.model.exceptions.UnverifiedException;
import am.basic.jdbcStart.model.exceptions.UserDataNotFoundError;
import am.basic.jdbcStart.service.UserManager;
import am.basic.jdbcStart.util.CookieUtil;
import am.basic.jdbcStart.util.Encoding.Encryptor;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


import static am.basic.jdbcStart.util.Constants.Message.*;
import static am.basic.jdbcStart.util.Constants.PagesLinks.*;
import static am.basic.jdbcStart.util.Constants.PagesLinks.INDEX_PAGE;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_MESSAGE_ATTRIBUTE_KEY;

public class StartServlet extends HttpServlet {
    private UserManager userManager = new UserManager();


    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie remCookie = CookieUtil.getByName(request, REMEMBER_TOKEN_COOKIE_KEY);
        if (remCookie == null) {
            response.sendRedirect(INDEX_PAGE);
            return;
        }
        String token=null;
        try {
             token=Encryptor.decrypt(remCookie.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String username = token.split(":")[0];
        String password = token.split(":")[1];


        try {
            User user = userManager.Login(username, password);
            HttpSession session = request.getSession();
            session.setAttribute(PARAMETER_USER_ATTRIBUTE_KEY, user);
            remCookie.setMaxAge(60 * 60 * 24 * 30);
            response.sendRedirect(HOME_PAGE);


        } catch (UnverifiedException e) {
            e.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            request.setAttribute(PARAMETER_USERNAME_KEY, username);
            request.getRequestDispatcher(VERIFY_USER_PAGE).forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserDataNotFoundError | InternalServerError u) {
            u.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, u.getMessage());
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        } catch (RuntimeException r) {
            r.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, INTERNAL_PROBLEM_MESSAGE);
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        }

    }
}

