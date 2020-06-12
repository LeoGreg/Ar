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

import static am.basic.jdbcStart.util.Constants.Message.CODE_SUCCESSFULLY_SEND_MESSAGE;
import static am.basic.jdbcStart.util.Constants.Message.INVALID_USERNAME_MESSAGE;
import static am.basic.jdbcStart.util.Constants.PagesLinks.VERIFY_USER_PAGE;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class SendCodeAnewServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(PARAMETER_USERNAME_KEY);
        try {
            InvalidParameterError.checkParameters(username == null || username.length() < 5, INVALID_USERNAME_MESSAGE);
            userManager.sendCodeAgain(username);
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY,CODE_SUCCESSFULLY_SEND_MESSAGE);
            request.setAttribute(PARAMETER_USERNAME_KEY,username);
            request.getRequestDispatcher(VERIFY_USER_PAGE).forward(request,response);
        } catch (InvalidParameterError | UserDataNotFoundError | InternalServerError i) {
            i.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY,i.getMessage());
            request.setAttribute(PARAMETER_USERNAME_KEY,username);
            request.getRequestDispatcher(VERIFY_USER_PAGE).forward(request,response);
        }catch(RuntimeException e){
            e.printStackTrace();
        }

    }
}
