package am.basic.jdbcStart.controller;

import am.basic.jdbcStart.model.Comment;
import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.model.exceptions.AddNothingError;
import am.basic.jdbcStart.repository.CommentRepository;
import am.basic.jdbcStart.service.CommentManager;
import am.basic.jdbcStart.service.UserManager;
import am.basic.jdbcStart.util.DataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static am.basic.jdbcStart.util.Constants.Message.NOTE_IS_NOT_ADDED_MESSAGE;
import static am.basic.jdbcStart.util.Constants.Message.TITLE_IS_NOT_ADDED_MESSAGE;
import static am.basic.jdbcStart.util.Constants.PagesLinks.HOME_PAGE;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.*;

public class AddCommentServlet extends HttpServlet {
    private CommentManager commentManager = new CommentManager();

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter(PARAMETER_TITLE_KEY);
        String note = request.getParameter(PARAMETER_NOTE_KEY);

        try {
            AddNothingError.isAdded(title == null,TITLE_IS_NOT_ADDED_MESSAGE );
            AddNothingError.isAdded(note == null, NOTE_IS_NOT_ADDED_MESSAGE);
            User user = (User) request.getSession().getAttribute(PARAMETER_USER_ATTRIBUTE_KEY);
            Comment comment = new Comment();
            comment.setUser_id(user.getId());
            comment.setNote(note);
            comment.setTitle(title);
            commentManager.add(comment);
            response.sendRedirect(HOME_PAGE);

        } catch (AddNothingError addNothingError) {
            addNothingError.printStackTrace();
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, addNothingError.getMessage());
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        }
    }
}
