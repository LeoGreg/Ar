package am.basic.jdbcStart.service;

import am.basic.jdbcStart.model.Comment;
import am.basic.jdbcStart.repository.CommentRepository;
import am.basic.jdbcStart.util.DataSource;

import java.sql.SQLException;
import java.util.List;

import static am.basic.jdbcStart.util.Constants.Message.INTERNAL_PROBLEM_MESSAGE;

public class CommentManager {
    private CommentRepository commentRepository = new CommentRepository(new DataSource());


    public List<Comment> getByUserId(int userId) throws SQLException {
        return commentRepository.getByUserId(userId);
    }


    public void add(Comment comment) throws InternalError {
        try {
            commentRepository.save(comment);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InternalError(INTERNAL_PROBLEM_MESSAGE);
        }
    }


    public void delete(int id) throws InternalError {
        try {
            commentRepository.delete(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InternalError(INTERNAL_PROBLEM_MESSAGE);
        }
    }

}
