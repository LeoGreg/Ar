package am.basic.jdbcStart.service;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.filter.exceptions.AccessDeniedException;
import am.basic.jdbcStart.filter.exceptions.DuplicateDataException;
import am.basic.jdbcStart.filter.exceptions.NotFoundException;
import am.basic.jdbcStart.filter.exceptions.UnverifiedException;
import am.basic.jdbcStart.repository.impl.UserRepository;
import am.basic.jdbcStart.repository.impl.spring.jpa.UserRepositorySpringJpaImpl;
import am.basic.jdbcStart.service.serviceInterfaces.UserServiceInterface;
import am.basic.jdbcStart.util.encoder.Generator;
import am.basic.jdbcStart.util.encoder.Md5Encoder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.sql.SQLException;

import static am.basic.jdbcStart.util.constants.Messages.*;
@Transactional
public class UserService   {

    private UserRepositorySpringJpaImpl userRepository;


    public void setUserRepository(UserRepositorySpringJpaImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void register(User user) {
//        User duplicate = userRepository.getByUsername(user.getUsername());
//        DuplicateDataException.check(duplicate != null, DUPLICATE_USER_MESSAGE);
        user.setPassword(Md5Encoder.encode(user.getPassword()));
        user.setCode(Generator.getRandomDigits(5));
        user.setStatus(0);
        userRepository.add(user);
    }

    public User login(String username, String password) throws NotFoundException, UnverifiedException, SQLException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null || !Md5Encoder.matches(password, user.getPassword()), INVALID_CREDENTIALS_MESSAGE);
        UnverifiedException.check(user.getStatus() != 1, UNVERIFIED_MESSAGE);
        return user;

    }


    public User changePassword(String username, String password, String newPassword) throws NotFoundException, AccessDeniedException, SQLException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        AccessDeniedException.check(!user.getPassword().equals(password), WRONG_PASSWORD_MESSAGE);
        user.setPassword(newPassword);
        userRepository.update(user);
        return user;
    }


    public void sendCode(String username) throws NotFoundException, SQLException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        user.setCode(Generator.getRandomDigits(5));
        //send code to user by email
        userRepository.update(user);
    }


    public void recoverPassword(String username, String code, String password) throws NotFoundException, AccessDeniedException, SQLException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        AccessDeniedException.check(!user.getCode().equals(code), WRONG_CODE_MESSAGE);
        user.setPassword(password);
        userRepository.update(user);
    }

    public void verify(String username, String code) throws NotFoundException, AccessDeniedException, SQLException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        AccessDeniedException.check(!user.getCode().equals(code), WRONG_CODE_MESSAGE);
        user.setStatus(1);
        userRepository.update(user);
    }

}
