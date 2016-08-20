package by.julia.bankservice.exception;

public class DAOFactoryException extends Exception {

	public DAOFactoryException() {
		super();
	}

	public DAOFactoryException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public DAOFactoryException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DAOFactoryException(String arg0) {
		super(arg0);
	}

	public DAOFactoryException(Throwable arg0) {
		super(arg0);
	}

}
