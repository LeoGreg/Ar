package am.basic.jdbcStart.service.serviceInterfaces;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.model.exceptions.AccessDeniedException;
import am.basic.jdbcStart.model.exceptions.DuplicateDataException;
import am.basic.jdbcStart.model.exceptions.NotFoundException;
import am.basic.jdbcStart.model.exceptions.UnverifiedException;


public interface UserServiceInterface {
   void register(User user) throws DuplicateDataException;

    User login(String username, String password) throws NotFoundException, UnverifiedException;

    User changePassword(String username, String password, String newPassword) throws NotFoundException, AccessDeniedException;

    void sendCode(String username) throws NotFoundException;

    void recoverPassword(String username, String code, String password) throws NotFoundException, AccessDeniedException;

    void verify(String username, String code) throws NotFoundException, AccessDeniedException;
}
