package am.basic.jdbcStart.filter.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(Throwable throwable) {
        super(throwable);
    }
}
