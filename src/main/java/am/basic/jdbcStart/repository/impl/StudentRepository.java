package am.basic.jdbcStart.repository.impl;

import am.basic.jdbcStart.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {
    void update(Student student) throws SQLException;

    Student getByNameAndSurname(String name, String surname) throws SQLException;

    List<Student> getAll() throws SQLException;

    Student getById(int id) throws SQLException, InterruptedException;

    List<Student> findByNameAndSurname(String name, String surname) throws SQLException;

    void add(Student student) throws SQLException;

    void delete(int id) throws SQLException;

    void transfer(Student from, Student to, int amount);
}
