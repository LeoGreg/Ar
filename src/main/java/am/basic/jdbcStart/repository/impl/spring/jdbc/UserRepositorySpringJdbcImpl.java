package am.basic.jdbcStart.repository.impl.spring.jdbc;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.repository.impl.UserRepository;
import com.mysql.jdbc.PreparedStatement;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
public class UserRepositorySpringJdbcImpl implements UserRepository {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(User user) {
        String query = "INSERT INTO user(code,name,password,status,surname,username) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(query, user.getCode(), user.getName(), user.getPassword(), user.getStatus(), user.getSurname(), user.getUsername());
    }

    @Override
    public void update(User user) {
//        String SQL = "update user set name = ? where id = ?";
//        jdbcTemplate.update(SQL, new Object[]{"Zara", 10});

    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM user";
        List<User> list = jdbcTemplate.query(query, new UserMapper());
        return list;
    }

    @Override
    public List<User> findByName(String name) {
        String query = "SELECT * FROM user WHERE name LIKE('%',?,'%')";
        return jdbcTemplate.query(query, new UserMapper());
    }

    @Override
    public List<User> findByNameAndSurname(String name, String surname) {
        String query = "SELECT * FROM user WHERE name LIKE('%',?,'%') AND surname LIKE('%',?,'%')";
        return jdbcTemplate.query(query, new UserMapper());


    }

    @Override
    public User getById(int id) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = " + id, new UserMapper());
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE username = " + username, new UserMapper());
        return user;
    }


    @Override
    public void delete(User user) {
//        String SQL = "delete from user  where id = ?";
//        jdbcTemplate.update( SQL, new Object[]{10} );
    }

    public static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet sqlRowSet, int i) throws SQLException {
            User user = new User();
            user.setId(sqlRowSet.getInt("id"));
            user.setName(sqlRowSet.getString("name"));
            user.setSurname(sqlRowSet.getString("surname"));
            user.setCode(sqlRowSet.getString("code"));
            user.setUsername(sqlRowSet.getString("username"));
            user.setPassword(sqlRowSet.getString("password"));
            user.setStatus(sqlRowSet.getInt("status"));
            return user;
        }
    }
}
