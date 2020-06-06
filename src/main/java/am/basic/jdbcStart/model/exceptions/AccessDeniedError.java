package am.basic.jdbcStart.model.exceptions;

public class AccessDeniedError extends Exception {

    public AccessDeniedError(String message) {
        super(message);
    }


    public static void check(boolean expresion, String message) throws AccessDeniedError {
        if (expresion) {
            throw new AccessDeniedError(message);
        }
    }
}
