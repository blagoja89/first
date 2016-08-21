package by.mavs.news.exception;


public class DaoException extends NewsException {
	   


	    /**
	 * 
	 */
	private static final long serialVersionUID = 9165857846764491323L;


		public DaoException() {
	        super();
	    }

	    
	    public DaoException(String message) {
	        super(message);
	    }

	    
	    public DaoException(String message, Exception cause) {
	        super(message, cause);
	    }

	    
	    public DaoException(Exception cause) {
	        super(cause);
	    }

	    
	    protected DaoException(String message, Exception cause,
	                             boolean enableSuppression,
	                             boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }

	}