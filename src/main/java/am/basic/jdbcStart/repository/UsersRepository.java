package am.basic.jdbcStart.repository;

import am.basic.jdbcStart.model.Users;
import am.basic.jdbcStart.util.DataSource;

import javax.swing.text.html.HTMLDocument;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {
    private DataSource dataSource;

    public UsersRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Users checkLogin(String email, String password) throws SQLException {
        Users users = null;
        Connection connection = dataSource.getConnection();
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            users = fromResultSet(resultSet);
        }
        connection.close();
        return users;


    }

    public Users fromResultSet(ResultSet resultSet) throws SQLException {
        Users users = new Users();

        users.setEmail(resultSet.getString("email"));
        users.setFullname(resultSet.getString("fullname"));
        return users;
    }
}
