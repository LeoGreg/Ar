package am.basic.jdbcStart.model.exceptions;

public class UserDataNotFoundError extends Exception {
    public UserDataNotFoundError(String message) {
        super(message);
    }

    public static void checkUserExistence(boolean expression, String message) throws UserDataNotFoundError {
        if (expression) {
            throw new UserDataNotFoundError(message);
        }
    }

}
