package am.basic.jdbcStart.repository;

import am.basic.jdbcStart.model.Comment;
import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.util.DataSource;
import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository {
    private DataSource dataSource;

    public CommentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void save(Comment comment) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comment(title,note,user_id) VALUES(?,?,?)");
        preparedStatement.setString(1, comment.getTitle());
        preparedStatement.setString(2, comment.getNote());
        preparedStatement.setInt(3, comment.getId());
        preparedStatement.executeUpdate();

    }

    public void delete(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM  comment WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    public List<Comment> getByUserId(int user_id) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("Select * FROM comment where user_id = ?");
        statement.setInt(1, user_id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Comment comment = fromResultSet(resultSet);
            comments.add(comment);
        }
        resultSet.close();
        statement.close();
        return comments;
    }


    private Comment fromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getInt("id"));
        comment.setTitle(resultSet.getString("title"));
        comment.setNote(resultSet.getString("note"));
        comment.setUser_id(resultSet.getInt("user_id"));
        return comment;
    }
}
