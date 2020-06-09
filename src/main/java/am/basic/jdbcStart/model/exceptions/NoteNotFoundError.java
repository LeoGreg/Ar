package am.basic.jdbcStart.model.exceptions;

public class NoteNotFoundError extends Exception {
    NoteNotFoundError(String message) {
        super(message);
    }

    public static void isAvailable(boolean expression, String message) throws NoteNotFoundError {
        if (expression) {
            throw new NoteNotFoundError(message);
        }
    }
}
