package am.basic.jdbcStart.service.serviceInterfaces;

import am.basic.jdbcStart.model.Comment;
import am.basic.jdbcStart.filter.exceptions.InternalServerException;

import java.sql.SQLException;
import java.util.List;

public interface CommentServiceInterface {
    List<Comment> getByUserId(int userId) throws SQLException;

    void add(Comment comment) throws InternalServerException;

    void delete(int id) throws InternalServerException;
}
