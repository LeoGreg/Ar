package am.basic.jdbcStart.repository.impl;

import am.basic.jdbcStart.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentRepository {
    List<Comment> getByUserId(int userId) throws SQLException;

    void save(Comment comment) throws SQLException;

    void delete(Comment comment) throws SQLException;

    Comment getById(int id) throws SQLException;
}
