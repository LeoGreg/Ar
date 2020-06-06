package am.basic.jdbcStart.model.exceptions;

public class InternalServerError extends Exception {
    public InternalServerError(String message){
        super(message);
    }
    public static void check(boolean expression, String message) throws InternalServerError {
        if (expression) {
            throw new InternalServerError(message);
        }
    }
}
