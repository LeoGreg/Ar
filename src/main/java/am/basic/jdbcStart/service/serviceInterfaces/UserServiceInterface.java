package am.basic.jdbcStart.service.serviceInterfaces;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.filter.exceptions.AccessDeniedException;
import am.basic.jdbcStart.filter.exceptions.DuplicateDataException;
import am.basic.jdbcStart.filter.exceptions.NotFoundException;
import am.basic.jdbcStart.filter.exceptions.UnverifiedException;

import java.sql.SQLException;
import java.util.List;


public interface UserServiceInterface {
   void register(User user) throws DuplicateDataException, SQLException;

    User login(String username, String password) throws NotFoundException, UnverifiedException, SQLException;

    User changePassword(String username, String password, String newPassword) throws NotFoundException, AccessDeniedException, SQLException;

    void sendCode(String username) throws NotFoundException, SQLException;

    void recoverPassword(String username, String code, String password) throws NotFoundException, AccessDeniedException, SQLException;

    void verify(String username, String code) throws NotFoundException, AccessDeniedException, SQLException;

}
