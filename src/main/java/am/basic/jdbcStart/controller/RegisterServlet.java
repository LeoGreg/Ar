package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.model.exceptions.*;
import am.basic.jdbcStart.service.UserManager;
import am.basic.jdbcStart.util.PasswordValidatorDepartment;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static am.basic.jdbcStart.util.Constants.Message.*;
import static am.basic.jdbcStart.util.Constants.PagesLinks.*;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class RegisterServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(PARAMETER_NAME_KEY);
        String surname = request.getParameter(PARAMETER_SURNAME_KEY);
        String username = request.getParameter(PARAMETER_USERNAME_KEY);
        String password = request.getParameter(PARAMETER_PASSWORD_KEY);

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setPassword(password);

        try {
            IllegalParametersForPasswordError.checkPasswordRequirements(PasswordValidatorDepartment.checkPassword(password), INVALID_PASSWORD_MESSAGE);
            InvalidParameterError.checkParameters(username == null || username.length() < 5, INVALID_USERNAME_MESSAGE);
            userManager.register(user);

            request.setAttribute(PARAMETER_USERNAME_KEY, username);
            request.getRequestDispatcher(VERIFY_USER_PAGE).forward(request, response);


        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
        } catch (IllegalParametersForPasswordError e) {
            e.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
        } catch (DuplicateDataError | InternalServerError e) {
            e.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
        } catch (InvalidParameterError invalidParameterError) {
            invalidParameterError.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, INVALID_USERNAME_MESSAGE);
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
        }catch(RuntimeException e){
            e.printStackTrace();
        }

        request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, SUCCESSFUL_REGISTER_MESSAGE);
        request.getRequestDispatcher(INDEX_PAGE).forward(request, response);

//        request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, SUCCESSFUL_REGISTER_MESSAGE);

//        RequestDispatcher rd = getServletContext().getRequestDispatcher(INDEX_PAGE);
//        rd.forward(request, response);


    }
}
