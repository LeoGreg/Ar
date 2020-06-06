package am.basic.jdbcStart.model.exceptions;

public class IllegalParametersForPasswordError extends Exception {
    public IllegalParametersForPasswordError(String message){
        super(message);
    }
    public static void checkPasswordRequirements(boolean expression,String message) throws IllegalParametersForPasswordError {
        if(expression){//true
                throw  new IllegalParametersForPasswordError(message);


        }

    }
}
