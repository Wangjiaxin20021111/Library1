package demo13.Exception;

/**
 * @author 25043
 */
public class DaoException extends RuntimeException{
    public DaoException() {
        super();
    }

    public DaoException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public DaoException(String exceptionMessage, Throwable e) {
        super(exceptionMessage, e);
    }
}
