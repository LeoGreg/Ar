package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.model.exceptions.AddNothingError;
import am.basic.jdbcStart.service.CommentManager;
import sun.management.HotspotMemoryMBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static am.basic.jdbcStart.util.Constants.Message.NOTE_IS_NOT_ADDED_MESSAGE;
import static am.basic.jdbcStart.util.Constants.Message.TITLE_IS_NOT_ADDED_MESSAGE;
import static am.basic.jdbcStart.util.Constants.PagesLinks.HOME_PAGE;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class DeleteCommentServlet extends HttpServlet {
    private CommentManager commentManager = new CommentManager();

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String StringId = request.getParameter(PARAMETER_ID_KEY);
            int id = Integer.parseInt(StringId);
            commentManager.delete(id);
            response.sendRedirect(HOME_PAGE);
        } catch (InternalError | RuntimeException e) {
            e.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, e.getMessage());
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        }
    }
}
