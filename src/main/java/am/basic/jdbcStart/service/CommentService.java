package am.basic.jdbcStart.service;

import am.basic.jdbcStart.model.Comment;
import am.basic.jdbcStart.filter.exceptions.InternalServerException;
import am.basic.jdbcStart.repository.impl.CommentRepository;
import am.basic.jdbcStart.repository.impl.spring.jpa.CommentRepositorySpringJpaImpl;
import am.basic.jdbcStart.service.serviceInterfaces.CommentServiceInterface;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static am.basic.jdbcStart.util.constants.Messages.INTERNAL_ERROR_MESSAGE;

@Transactional
public class CommentService {

    private CommentRepositorySpringJpaImpl commentRepository;

    public CommentRepositorySpringJpaImpl getCommentRepository() {
        return commentRepository;
    }

    public void setCommentRepository(CommentRepositorySpringJpaImpl commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getByUserId(int userId) throws SQLException {
        return commentRepository.getByUserId(userId);
    }

    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    public void delete(int id) throws InternalServerException {
        Comment comment = commentRepository.getById(id);
        commentRepository.delete(comment);
    }
}
