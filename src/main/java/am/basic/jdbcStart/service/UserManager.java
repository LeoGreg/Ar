package am.basic.jdbcStart.service;

import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.model.exceptions.*;
import am.basic.jdbcStart.repository.UserRepository;
import am.basic.jdbcStart.util.*;
import am.basic.jdbcStart.util.Encoding.Md5Encoder;


import java.sql.SQLException;

import static am.basic.jdbcStart.util.Constants.Message.*;


public class UserManager {
    private UserRepository userRepository = new UserRepository(new DataSource());

    public void register(User user) throws SQLException, DuplicateDataError, InternalServerError {

        try {
            User processingClient = userRepository.CheckIfThereAreClientsWithTheSameUserName(user.getUsername());
            DuplicateDataError.checkIfDuplicated(processingClient != null, DUPLICATE_USER_MESSAGE);
            user.setCode(Generator.getRandomDigits(5));
            user.setPassword(Md5Encoder.encode(user.getPassword()));
            user.setStatus(0);
            userRepository.INSERT_INTO(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerError(INTERNAL_PROBLEM_MESSAGE);

        }
    }

    public void sendCodeAgain(String username) throws UserDataNotFoundError, InternalServerError {

        try {
            User client = userRepository.getByUsername(username);
            UserDataNotFoundError.checkUserExistence(client == null, USER_NOT_EXIST_MESSAGE);
            client.setCode(Generator.getRandomDigits(5));
            userRepository.update(client);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerError(INTERNAL_PROBLEM_MESSAGE);

        }
    }


    public void forgetPassword(String username) throws InternalServerError, UserDataNotFoundError {
        try {
            User user = userRepository.getByUsername(username);

            UserDataNotFoundError.checkUserExistence(user == null, USER_NOT_EXIST_MESSAGE);

            String newCode = CodeGenerator.getGeneratedCode(5);
            userRepository.updateCodeByUsername(newCode, username);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerError(INTERNAL_PROBLEM_MESSAGE);
        }
    }

    public void generateCode(String username) throws InternalServerError {
        try {
            User user = userRepository.getByUsername(username);
            user.setCode(Generator.getRandomDigits(5));
            userRepository.updateCode(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerError(INTERNAL_PROBLEM_MESSAGE);
        }
    }

    public void verifyUserByCode(String username, String code) throws InternalServerError, AccessDeniedError, UserDataNotFoundError {
        try {
            User user = userRepository.getByUsername(username);
            UserDataNotFoundError.checkUserExistence(user == null, USER_NOT_EXIST_MESSAGE);
            AccessDeniedError.check(!(user.getCode().equals(code)), NOT_MATCHING_CODE_MESSAGE);
            user.setStatus(1);
            userRepository.updateStatus(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerError(INTERNAL_PROBLEM_MESSAGE);
        }
    }

    public void checkUserStatus(String username) throws InternalServerError, AccessDeniedError, UserDataNotFoundError, StatusNotMatchingError {
        User user = null;
        try {
            user = userRepository.getByUsername(username);
            StatusNotMatchingError.checkStatusActivity(user.getStatus() == 0, STATUS_NOT_MATCHING_MESSAGE);
            UserDataNotFoundError.checkUserExistence(user == null, USER_NOT_EXIST_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerError(INTERNAL_PROBLEM_MESSAGE);
        }
//            UserDataNotFoundError.checkUserExistence(user==null,USER_NOT_EXIST_MESSAGE);
//            AccessDeniedError.check(!(user.getCode().equals(code)),NOT_MATCHING_CODE_MESSAGE);


    }


    public void recoverPassword(String username, String code, String newPassword) throws InternalServerError, UserDataNotFoundError, AccessDeniedError {
        try {
            User user = userRepository.getByUsername(username);
            UserDataNotFoundError.checkUserExistence(user == null, USER_NOT_EXIST_MESSAGE);
            AccessDeniedError.check(!(user.getCode().equals(code)), NOT_MATCHING_CODE_MESSAGE);
            user.setPassword(newPassword);
            userRepository.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerError(INTERNAL_PROBLEM_MESSAGE);
        }

    }


    public User changingPassword(String username, String oldPassword, String newPassword, String confirmingPassword) throws SQLException, IllegalParametersForPasswordError, UserDataNotFoundError, PasswordChangingError, AccessDeniedError, InternalServerError {
        User user = null;
        try {
            user = userRepository.getByUsername(username);
            UserDataNotFoundError.checkUserExistence(user == null, USER_NOT_EXIST_MESSAGE);
            IllegalParametersForPasswordError.checkPasswordRequirements(PasswordValidatorDepartment.checkPassword(oldPassword), INVALID_PASSWORD_MESSAGE);
            IllegalParametersForPasswordError.checkPasswordRequirements(PasswordValidatorDepartment.checkPassword(newPassword), INVALID_PASSWORD_MESSAGE);
            PasswordChangingError.checkEquality(!(newPassword.equals(confirmingPassword)), NOT_MATCHING_Passwords_MESSAGE);
            AccessDeniedError.check(!(user.getPassword().equals(oldPassword)), NOT_MATCHING_Passwords_MESSAGE);
            userRepository.changePassword(username, newPassword);

        } catch (UserDataNotFoundError e) {
            e.printStackTrace();
            throw new UserDataNotFoundError(USER_NOT_EXIST_MESSAGE);
        } catch (IllegalParametersForPasswordError e) {
            e.printStackTrace();
            throw new IllegalParametersForPasswordError(INVALID_PASSWORD_MESSAGE);

        } catch (PasswordChangingError e) {
            e.printStackTrace();
            throw new PasswordChangingError(NOT_MATCHING_Passwords_MESSAGE);
        } catch (AccessDeniedError e) {
            e.printStackTrace();
            throw new AccessDeniedError(NOT_MATCHING_Passwords_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerError(INTERNAL_PROBLEM_MESSAGE);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return user;

    }


    public User Login(String username, String password) throws SQLException, UserDataNotFoundError, InternalServerError, UnverifiedException {
        try {
            User user = userRepository.getByUsername(username);
            UserDataNotFoundError.checkUserExistence(user == null||!Md5Encoder.matches(password,user.getPassword()), INVALID_CREDENTIALS_MESSAGE);
            UnverifiedException.check(user.getStatus() != 1, UNVERIFIED_MESSAGE);
            InternalServerError.check(user == null, INTERNAL_PROBLEM_MESSAGE);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InternalServerError(INTERNAL_PROBLEM_MESSAGE);
        }
    }
}
