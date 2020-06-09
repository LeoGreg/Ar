package am.basic.jdbcStart.repository;

import am.basic.jdbcStart.model.Note;
import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    private DataSource dataSource;

    public NoteRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void writeDownSomeText(int user_id, String title, String writing) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO note(writing,title,user_id) VALUES(?,?,?)");
        preparedStatement.setString(1, writing);
        preparedStatement.setString(2, title);
        preparedStatement.setInt(3, user_id);
        int result = preparedStatement.executeUpdate();

    }


    public void delete(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM note WHERE user_id= ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }


    public ArrayList<Note> getNote(int user_id) throws SQLException {
        ArrayList<Note> notes = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT title ,writing  FROM note WHERE user_id=? ");
        statement.setInt(1, user_id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            notes.add(fromResultSet(resultSet));
        }
        resultSet.close();
        statement.close();
        return notes;
    }


    private Note fromResultSet(ResultSet resultSet) throws SQLException {
        Note note = new Note();
        note.setTitle(resultSet.getString("title"));
        note.setWriting(resultSet.getString("writing"));

        return note;
    }
}
