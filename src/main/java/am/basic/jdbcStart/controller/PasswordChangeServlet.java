package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.model.exceptions.*;
import am.basic.jdbcStart.service.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static am.basic.jdbcStart.util.Constants.Message.*;
import static am.basic.jdbcStart.util.Constants.PagesLinks.*;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class PasswordChangeServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter(PARAMETER_PASSWORD_KEY);
        String newPassword = request.getParameter(PARAMETER_CHANGING_PASSWORD_KEY);
        String confirmingPassword = request.getParameter(PARAMETER_CONFIRMING_PASSWORD_KEY);
        try {

            HttpSession httpSession = request.getSession();
            User userSession = (User) httpSession.getAttribute(PARAMETER_USER_ATTRIBUTE_KEY);//user in session
            UnauthorizedError.checkSessionExpiry(userSession == null, SESSION_EXPIRED_MESSAGE);
            User user = userManager.changingPassword(userSession.getUsername(), oldPassword, newPassword, confirmingPassword);
            request.setAttribute(PARAMETER_USER_ATTRIBUTE_KEY, user);
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, SUCCESSFUL_PASSWORD_CHANGING);
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } catch (InternalServerError i) {
            i.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, i.getMessage());
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        } catch (UnauthorizedError u) {
            u.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, u.getMessage());
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } catch (UserDataNotFoundError us) {
            us.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, us.getMessage());
            request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
        } catch (AccessDeniedError a) {
            a.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, a.getMessage());
            request.getRequestDispatcher(CHANGING_PAGE).forward(request, response);
        } catch (IllegalParametersForPasswordError i) {
            i.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, i.getMessage());
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } catch (PasswordChangingError p) {
            p.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, p.getMessage());
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
