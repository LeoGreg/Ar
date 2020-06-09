package am.basic.jdbcStart.model.exceptions;

public class AddNothingError extends Exception {

    public AddNothingError(String message) {
        super(message);
    }


    public static void isAdded(boolean expresion, String message) throws AddNothingError {
        if (expresion) {
            throw new AddNothingError(message);
        }

    }
}