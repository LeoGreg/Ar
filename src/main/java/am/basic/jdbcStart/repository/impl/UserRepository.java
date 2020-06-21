package am.basic.jdbcStart.repository.impl;

import am.basic.jdbcStart.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    void add(User user);

    void update(User user);

    List<User> getAll();

    List<User> findByName(String name);

    List<User> findByNameAndSurname(String name, String surname);

    User getById(int id);

    User getByUsername(String username) throws SQLException;

    void delete(User user);
}
