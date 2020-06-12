package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.model.exceptions.*;
import am.basic.jdbcStart.service.UserManager;
import am.basic.jdbcStart.util.Encoding.Encryptor;
import am.basic.jdbcStart.util.PasswordValidatorDepartment;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.*;


import java.io.IOException;
import java.sql.SQLException;


import static am.basic.jdbcStart.util.Constants.Message.*;
import static am.basic.jdbcStart.util.Constants.PagesLinks.*;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class LoginServlet extends HttpServlet {
    private UserManager userManager = new UserManager();


    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(PARAMETER_USERNAME_KEY);
        String password = request.getParameter(PARAMETER_PASSWORD_KEY);
        String rememberMe = request.getParameter(PARAMETER_REMEMBER_ATTRIBUTE_KEY);
        try {
            InvalidParameterError.checkParameters(username == null || username.length() < 5, INVALID_USERNAME_MESSAGE);
            InvalidParameterError.checkParameters(PasswordValidatorDepartment.checkPassword(password), INVALID_PASSWORD_MESSAGE);
            User user = userManager.Login(username, password);
            HttpSession session = request.getSession();
            session.setAttribute(PARAMETER_USER_ATTRIBUTE_KEY, user);

            if (rememberMe != null && rememberMe.equalsIgnoreCase("on")) {
                Cookie cookie = new Cookie(REMEMBER_TOKEN_COOKIE_KEY, Encryptor.encrypt(username + ":" + password));
                cookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(cookie);
            }



            response.sendRedirect(HOME_PAGE);

        } catch (UnverifiedException e) {
            e.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            request.setAttribute(PARAMETER_USERNAME_KEY, username);
            request.getRequestDispatcher(VERIFY_USER_PAGE).forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserDataNotFoundError | InvalidParameterError | InternalServerError u) {
            u.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, u.getMessage());
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        } catch (RuntimeException r) {
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, INTERNAL_PROBLEM_MESSAGE);
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
