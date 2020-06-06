package am.basic.jdbcStart.model.exceptions;


public class StatusNotMatchingError extends Exception {
    public StatusNotMatchingError(String message) {
        super(message);
    }

    public static void checkStatusActivity(boolean expression, String message) throws StatusNotMatchingError {
        if (expression) {
            throw new StatusNotMatchingError(message);
        }
    }
}

