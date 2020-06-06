package am.basic.jdbcStart.model.exceptions;

public class DuplicateDataError extends Exception {
    public DuplicateDataError(String message){
        super(message);
    }
    public static void checkIfDuplicated(boolean expression, String message) throws DuplicateDataError {
        if(expression){
            throw new DuplicateDataError(message);
        }
    }
}
