package by.julia.bankservice.exception;

public class PoolTechnicalException extends Exception {

	private static final long serialVersionUID = 1L;

	public PoolTechnicalException() {
	}

	public PoolTechnicalException(String message) {
		super(message);
	}

	public PoolTechnicalException(Throwable cause) {
		super(cause);
	}

	public PoolTechnicalException(String message, Throwable cause) {
		super(message, cause);
	}
}
