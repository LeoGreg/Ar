package am.basic.jdbcStart.repository;

import am.basic.jdbcStart.model.Images;
import am.basic.jdbcStart.model.Student;
import am.basic.jdbcStart.util.DataSource;

import java.nio.file.ReadOnlyFileSystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImagesRepository {
    private static DataSource dataSource;

    public ImagesRepository(DataSource da) {
        this.dataSource = da;
    }

    //encoding path of image
    public static byte[] encode(byte[] content, String key) {
        int start = 0;

        for (int i = 0; i < content.length; i++) {
            content[i] = (byte) (content[i] - key.charAt(start));
            start++;
            if (start == content.length) {
                start = 0;
            }
        }
        return content;
    }

    //decoding image path
    public static byte[] decode(byte[] content, String key) {
        int start = 0;

        for (int i = 0; i < content.length; i++) {
            content[i] = (byte) (content[i] + key.charAt(start));
            start++;
            if (start == content.length) {
                start = 0;
            }
        }
        return content;
    }


    public void add_picture(Images img) throws SQLException {//encode
        Connection connection = dataSource.getConnection();
        String path = img.getPath();
        byte[] array = encode(path.getBytes(), "StopMeIfYouCan");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO images(path)VALUES(?)");
        preparedStatement.setString(1, array.toString());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public Images getPicById(int id) throws SQLException {//decode
        Images img = null;
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM images WHERE id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            img = fromResultSet(resultSet);
        }
        resultSet.close();
        preparedStatement.close();
        byte[] array = decode(img.getPath().getBytes(), "StopMeIfYouCan");//decoding path
        return img;
    }

    private Images fromResultSet(ResultSet resultSet) throws SQLException {///////////////
        Images img = new Images();
        img.setId(resultSet.getInt("id"));
        img.setPath(resultSet.getString("path"));

        return img;
    }


}
