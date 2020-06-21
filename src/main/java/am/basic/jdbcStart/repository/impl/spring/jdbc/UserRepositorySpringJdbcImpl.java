package am.basic.jdbcStart.repository.impl.spring.jdbc;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.repository.impl.UserRepository;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

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

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }

    @Override
    public List<User> findByNameAndSurname(String name, String surname) {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        User user = null;
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT * FROM user WHERE username=?", username);
        while (sqlRowSet.next()) {
            user = fromRowSet(sqlRowSet);
        }
        return user;
    }

    private User fromRowSet(SqlRowSet sqlRowSet){
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

    @Override
    public void delete(User user) {

    }
}
