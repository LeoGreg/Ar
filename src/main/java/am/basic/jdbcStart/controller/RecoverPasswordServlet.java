package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.exceptions.*;
import am.basic.jdbcStart.service.UserManager;
import am.basic.jdbcStart.util.PasswordValidatorDepartment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static am.basic.jdbcStart.util.Constants.Message.*;
import static am.basic.jdbcStart.util.Constants.PagesLinks.INDEX_PAGE;
import static am.basic.jdbcStart.util.Constants.PagesLinks.RECOVER_PASSWORD_PAGE;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class RecoverPasswordServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(PARAMETER_USERNAME_KEY);
        String code = request.getParameter(PARAMETER_CODE_KEY);
        String newPassword = request.getParameter(PARAMETER_PASSWORD_KEY);
        try {
            InvalidParameterError.checkParameters(username == null || username.length() < 5, INVALID_USERNAME_MESSAGE);
            InvalidParameterError.checkParameters(code == null || code.length() !=5 , INVALID_CODE_MESSAGE);
            IllegalParametersForPasswordError.checkPasswordRequirements(PasswordValidatorDepartment.checkPassword(newPassword), INVALID_PASSWORD_MESSAGE);
            userManager.recoverPassword(username, code, newPassword);
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, SUCCESSFUL_PASSWORD_CHANGING);
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);

        } catch (IllegalParametersForPasswordError | InvalidParameterError | InternalServerError | AccessDeniedError | UserDataNotFoundError e) {
            e.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            request.getRequestDispatcher(RECOVER_PASSWORD_PAGE).forward(request, response);
        }catch(RuntimeException e){
            e.printStackTrace();
        }
    }
}
