package by.mavs.news.exception;

public class NewsException extends Exception {
	   
	private static final long serialVersionUID = 1343496147896122607L;


    public NewsException() {
        super();
    }

   
    public NewsException(String message) {
        super(message);
    }

  
    public NewsException(String message, Exception cause) {
        super(message, cause);
    }

    
    public NewsException(Exception cause) {
        super(cause);
    }

    
    protected NewsException(String message, Exception cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}