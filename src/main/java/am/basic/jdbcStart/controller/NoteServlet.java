package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.Note;
import am.basic.jdbcStart.model.exceptions.*;
import am.basic.jdbcStart.service.UserManager;
import am.basic.jdbcStart.util.CookieUtil;
import am.basic.jdbcStart.util.Encoding.Encryptor;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Encoder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static am.basic.jdbcStart.util.Constants.Message.*;
import static am.basic.jdbcStart.util.Constants.PagesLinks.*;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class NoteServlet extends HttpServlet {
    private UserManager userManager = new UserManager();


    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter(PARAMETER_TITLE_KEY);
        String text = request.getParameter(PARAMETER_TEXT_KEY);
        Cookie textCookie = CookieUtil.getByName(request, ADD_COOKIE_NAME);
        String submit = request.getParameter(PARAMETER_SUBMIT_BUTTON_KEY);
        String click = request.getParameter(PARAMETER_CLICK_BUTTON_KEY);
        String clickR = request.getParameter(PARAMETER_CLICKR_KEY);


        if (textCookie == null) {
            response.sendRedirect(HOME_PAGE);
            return;
        }
        if (submit != null && text != null) {
            String token = null;
            try {
                token = Encryptor.decrypt(textCookie.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String username = token;

                userManager.add(username, title, text);
                request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, SUCCESSFULLY_ADDED_NOTE);
                request.getRequestDispatcher(HOME_PAGE).forward(request, response);
            } catch (InternalServerError internalServerError) {
                internalServerError.printStackTrace();
            } catch (AddNothingError a) {
                a.printStackTrace();
                request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, a.getMessage());
                request.getRequestDispatcher(HOME_PAGE).forward(request, response);
            }

        }
        if (submit != null && click != null) {


            String token = null;
            try {
                token = Encryptor.decrypt(textCookie.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String username = token;
                ArrayList<Note> note = userManager.seeNotes(username);
                request.setAttribute(NOTE_GET_KEY, note);
                request.getRequestDispatcher(NOTE_READ_PAGE).forward(request, response);
            } catch (InternalServerError internalServerError) {
                internalServerError.printStackTrace();
            } catch (NoteNotFoundError a) {
                a.printStackTrace();
                request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, a.getMessage());
                request.getRequestDispatcher(HOME_PAGE).forward(request, response);
            }


        }
        if (submit != null && clickR != null) {
            String token = null;
            try {
                token = Encryptor.decrypt(textCookie.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String username = token;
                userManager.removeAll(username);
                request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, NOTES_HAS_SUCCESSFULLY_BEEN_ERASED);
                request.getRequestDispatcher(HOME_PAGE).forward(request, response);
            } catch (InternalServerError | noteExistenceCheckError | InvalidParameterError internalServerError) {
                internalServerError.printStackTrace();
                request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, internalServerError.getMessage());
                request.getRequestDispatcher(HOME_PAGE).forward(request, response);
            }

        }
    }
}