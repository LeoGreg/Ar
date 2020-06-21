package am.basic.jdbcStart.service;

import am.basic.jdbcStart.model.Comment;
import am.basic.jdbcStart.model.exceptions.InternalServerException;
import am.basic.jdbcStart.repository.impl.CommentRepository;
import am.basic.jdbcStart.service.serviceInterfaces.CommentServiceInterface;

import java.sql.SQLException;
import java.util.List;

import static am.basic.jdbcStart.util.constants.Messages.INTERNAL_ERROR_MESSAGE;

public class CommentService implements CommentServiceInterface {


    private CommentRepository commentRepository ;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository=commentRepository;
    }



    @Override
    public List<Comment> getByUserId(int userId) throws SQLException {
        return commentRepository.getByUserId(userId);
    }
    @Override
    public void add(Comment comment) throws InternalServerException {
        try {
            commentRepository.save(comment);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InternalServerException(INTERNAL_ERROR_MESSAGE);
        }
    }
    @Override
    public void delete(int id) throws InternalServerException {
        try {
            Comment comment = commentRepository.getById(id);
            commentRepository.delete(comment);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InternalServerException(INTERNAL_ERROR_MESSAGE);
        }
    }
}
