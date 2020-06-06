package am.basic.jdbcStart.repository;

import am.basic.jdbcStart.model.Note;
import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteRepository {

    private DataSource dataSource;

    public NoteRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void writeDownSomeText(Note note) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT into note VALUES(?,?)");
        preparedStatement.setInt(1, note.getUserId());
        preparedStatement.setString(2, note.getWriting());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void update(Note note) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE note SET  userId=?,writing=?");
        preparedStatement.setInt(1, note.getUserId());
        preparedStatement.setString(2, note.getWriting());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void delete(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM note WHERE userId= ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }


    public Note getNote(int id) throws SQLException {
        Note note = null;
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("Select * FROM note where userId = ? ");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            note = fromResultSet(resultSet);
        }
        resultSet.close();
        statement.close();
        return note;
    }


    private Note fromResultSet(ResultSet resultSet) throws SQLException {
        Note note = new Note();
        note.setUserId(resultSet.getInt("userId"));
        note.setWriting(resultSet.getString("writing"));

        return note;
    }
}
