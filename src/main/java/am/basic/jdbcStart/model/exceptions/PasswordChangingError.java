package am.basic.jdbcStart.model.exceptions;

public class PasswordChangingError extends Exception {
    public PasswordChangingError(String message){
        super(message);
    }
    public static void checkEquality(boolean expression,String message) throws PasswordChangingError {
        if(expression){
            throw new PasswordChangingError(message);
        }
    }
}
