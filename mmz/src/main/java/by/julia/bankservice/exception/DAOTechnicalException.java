package by.julia.bankservice.exception;

public class DAOTechnicalException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOTechnicalException() {
	}

	public DAOTechnicalException(String arg0) {
		super(arg0);
	}

	public DAOTechnicalException(Throwable arg0) {
		super(arg0);
	}

	public DAOTechnicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
