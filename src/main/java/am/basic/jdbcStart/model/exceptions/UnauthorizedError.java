package am.basic.jdbcStart.model.exceptions;

public class UnauthorizedError extends Exception {
    public UnauthorizedError(String message) {
        super(message);
    }

    public static void checkSessionExpiry(boolean expression, String message) throws UnauthorizedError {
        if (expression) {
            throw new UnauthorizedError(message);
        }
    }
}
