package am.basic.jdbcStart.repository.impl;

import am.basic.jdbcStart.model.Book;
import am.basic.jdbcStart.model.Faculty;
import am.basic.jdbcStart.model.Student;
import am.basic.jdbcStart.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {
    void update(Student student) throws SQLException;

    Student getByNameAndSurname(String name, String surname) throws SQLException;

    List<Student> getAll() throws SQLException;

    Student getById(int id) throws SQLException, InterruptedException;

    Student getByIdAll(int id);

    Faculty getByIdFaculty(int id);

    List<Book> getByIdBooks(int id);

    List<Teacher> getByIdTeachers(int id);

    List<Student> findByNameAndSurname(String name, String surname) throws SQLException;

    void add(Student student) throws SQLException;

    void delete(int id) throws SQLException;

    void transfer(int from, int to, int amount);
}
