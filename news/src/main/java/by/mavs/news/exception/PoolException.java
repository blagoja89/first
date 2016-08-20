package by.mavs.news.exception;


public class PoolException extends NewsException {

	private static final long serialVersionUID = 1L;

	public PoolException() {
        super();
    }

    
    public PoolException(String message) {
        super(message);
    }

   
    public PoolException(String message, Exception cause) {
        super(message, cause);
    }

   
    public PoolException(Exception cause) {
        super(cause);
    }

    
    protected PoolException(String message, Exception cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
