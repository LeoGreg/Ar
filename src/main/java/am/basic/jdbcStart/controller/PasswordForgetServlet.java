package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.exceptions.InternalServerError;
import am.basic.jdbcStart.model.exceptions.InvalidParameterError;
import am.basic.jdbcStart.model.exceptions.UserDataNotFoundError;
import am.basic.jdbcStart.service.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static am.basic.jdbcStart.util.Constants.Message.INVALID_USERNAME_MESSAGE;
import static am.basic.jdbcStart.util.Constants.PagesLinks.*;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class PasswordForgetServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(PARAMETER_USERNAME_KEY);
        try {
            InvalidParameterError.checkParameters(username == null || username.length() < 5, INVALID_USERNAME_MESSAGE);
            userManager.forgetPassword(username);
            request.setAttribute(PARAMETER_USERNAME_KEY, username);
            request.getRequestDispatcher(RECOVER_PASSWORD_PAGE).forward(request,response);

        } catch (InternalServerError | InvalidParameterError | UserDataNotFoundError e) {
            e.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            request.getRequestDispatcher(FORGET_PASSWORD_PAGE).forward(request, response);
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }
}

