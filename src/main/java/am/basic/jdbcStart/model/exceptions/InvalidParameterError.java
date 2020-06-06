package am.basic.jdbcStart.model.exceptions;

public class InvalidParameterError extends Exception {
    InvalidParameterError(String message) {
        super(message);
    }

    public static void checkParameters(boolean expression, String message) throws InvalidParameterError {
        if (expression) {
            throw new InvalidParameterError(message);
        }
    }
}
