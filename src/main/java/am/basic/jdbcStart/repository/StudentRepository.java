package am.basic.jdbcStart.repository;

import am.basic.jdbcStart.model.Student;
import am.basic.jdbcStart.util.DataSource;

import javax.swing.text.Style;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentRepository implements Cloneable {


    private static DataSource dataSource;

    public StudentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void update(Student student) throws SQLException {//////////
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  student SET  name = ? ,surname = ? WHERE  id  = ?");
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getSurname());
        preparedStatement.setInt(3, student.getId());

        int result = preparedStatement.executeUpdate();
        System.out.println("result = " + result);
    }

    public Student getByNameAndSurname(String name, String surname) throws SQLException {//////////////
        Student student = null;

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("Select * FROM student where name = ? and surname = ?");
        statement.setString(1, name);
        statement.setString(2, surname);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            student = fromResultSet(resultSet);
        }

        resultSet.close();
        statement.close();

        return student;
    }


    public List<Student> getAll() throws SQLException {/////////////
        List<Student> students = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("Select * FROM student ");

        while (resultSet.next()) {
            Student student = fromResultSet(resultSet);
            students.add(student);
        }

        resultSet.close();
        return students;
    }


    public Student getById(int id) throws SQLException, Exception {///////////////////////////////////////
        Student student = null;
        Connection connection = dataSource.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        PreparedStatement statement = connection.prepareStatement("Select * FROM student where id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            student = fromResultSet(resultSet);
        }
        resultSet.close();
        statement.close();
        return student;
    }


    public List<Student> findByNameAndSurname() throws SQLException {//////////////////

        Scanner input = new Scanner(System.in);

        List<Student> students = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("Select * FROM student where name LIKE(CONCAT('%',?,'%')) and surname LIKE(CONCAT('%',?,'%'))");
        statement.setString(1, input.nextLine());
        statement.setString(2, input.nextLine());
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Student student = fromResultSet(resultSet);
            students.add(student);
        }

        resultSet.close();
        statement.close();

        return students;
    }

    public void add(Student student) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT into student VALUES(0,?,?)");
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getSurname());

        int result = preparedStatement.executeUpdate();
        System.out.println("result = " + result);//////////////////////
    }


    public void delete() throws SQLException {
        System.out.println("input id = ");
        Scanner input = new Scanner(System.in);
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM student WHERE id = ?");
        statement.setInt(1, input.nextInt());
        statement.execute();
        statement.close();
    }


    private Student fromResultSet(ResultSet resultSet) throws SQLException {///////////////
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setSurname(resultSet.getString("surname"));
        student.setBalance(resultSet.getInt("balance"));
        return student;
    }

    public synchronized void transfer(Student from, Student to, int amount) throws SQLException, InterruptedException {///////////////////////////////////////

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            preparedStatement = connection.prepareStatement("UPDATE student set balance = ? WHERE id = ?");

            preparedStatement.setInt(1,from.getBalance()-amount);
            preparedStatement.setInt(2, from.getId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("UPDATE student set balance = ? WHERE id = ?");
            preparedStatement.setInt(1, to.getBalance() + amount);
            preparedStatement.setInt(2, to.getId());
            preparedStatement.executeUpdate();

            System.out.println("before commit");
            connection.commit();
            System.out.println("after commit commit");

        } catch (Throwable throwable) {
            System.out.println(throwable);
            try {
                connection.rollback();
            } catch (SQLException e) {

                System.out.println(e);
            }


        }
    }
   public  synchronized  void withdraw(Student from,int amount) throws SQLException {
        Connection connection= dataSource.getConnection();
        PreparedStatement preparedStatement=null;
        try{
            preparedStatement = connection.prepareStatement("UPDATE student SET balance =? WHERE id=?");
            preparedStatement.setInt(1, from.getBalance() - amount);
            preparedStatement.setInt(2, from.getId());

        }catch (Exception e){
            System.out.println(e);
        }

        if (from.getBalance() < amount) {
            System.out.println("less balance : waiting for deposit");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
       preparedStatement.executeUpdate();


        System.out.println("withdraw is completed");
    }
    public synchronized void deposit(Student student, int amount) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE student SET balance =? WHERE id=?");
            preparedStatement.setInt(1, student.getBalance() + amount);
            preparedStatement.setInt(2, student.getId());
            preparedStatement.executeUpdate();

            notify();
        } catch (SQLException e) {
            System.out.println(e);

        }

    }
}


