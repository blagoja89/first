package by.mavs.news.exception;

public class ActionException extends NewsException {
	  

		/**
	 * 
	 */
	private static final long serialVersionUID = 8626884319980773024L;


		public ActionException() {
	        super();
	    }

	   
	    public ActionException(String message) {
	        super(message);
	    }

	   
	    public ActionException(String message, Exception cause) {
	        super(message, cause);
	    }

	   
	    public ActionException(Exception cause) {
	        super(cause);
	    }

	   
	    protected ActionException(String message, Exception cause,
	                                boolean enableSuppression,
	                                boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
	}