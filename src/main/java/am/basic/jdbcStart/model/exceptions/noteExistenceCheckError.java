package am.basic.jdbcStart.model.exceptions;

public class noteExistenceCheckError extends Exception {
    noteExistenceCheckError(String message) {
        super(message);
    }

    public static void isAvailableNotes(boolean expression, String message) throws InvalidParameterError, noteExistenceCheckError {
        if (expression) {
            throw new noteExistenceCheckError(message);
        }
    }
}
