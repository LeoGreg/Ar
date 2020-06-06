package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.exceptions.AccessDeniedError;
import am.basic.jdbcStart.model.exceptions.InternalServerError;
import am.basic.jdbcStart.model.exceptions.InvalidParameterError;
import am.basic.jdbcStart.model.exceptions.UserDataNotFoundError;
import am.basic.jdbcStart.repository.UserRepository;
import am.basic.jdbcStart.service.UserManager;
import am.basic.jdbcStart.util.DataSource;
import am.basic.jdbcStart.util.Generator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static am.basic.jdbcStart.util.Constants.Message.*;
import static am.basic.jdbcStart.util.Constants.PagesLinks.INDEX_PAGE;
import static am.basic.jdbcStart.util.Constants.PagesLinks.VERIFY_USER_PAGE;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class VerificationServlet extends HttpServlet {
    private UserManager userManager = new UserManager();
    private UserRepository userRepository = new UserRepository(new DataSource());

    //private UserRepository userRepository= new UserRepository(new DataSource());
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(PARAMETER_USERNAME_KEY);
        String code = request.getParameter(PARAMETER_CODE_KEY);
        try {
            InvalidParameterError.checkParameters(username == null || username.length() < 5, INVALID_USERNAME_MESSAGE);
            InvalidParameterError.checkParameters(code == null || code.length() != 5, INVALID_CODE_MESSAGE);

            userManager.verifyUserByCode(username, code);
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, VERIFICATION_SUCCESS_MESSAGE);
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        } catch (UserDataNotFoundError | InvalidParameterError | InternalServerError | AccessDeniedError u) {
            u.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, u.getMessage());
            request.setAttribute(PARAMETER_USERNAME_KEY, username);
            try {
                userRepository.generateCode(Generator.getRandomDigits(5),username);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InternalServerError internalServerError) {
                internalServerError.printStackTrace();
            }
            request.getRequestDispatcher(VERIFY_USER_PAGE).forward(request, response);
        }

    }
}