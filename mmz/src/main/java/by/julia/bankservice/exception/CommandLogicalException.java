package by.julia.bankservice.exception;

public class CommandLogicalException extends Exception {

	public CommandLogicalException() {
		super();
	}

	public CommandLogicalException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public CommandLogicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CommandLogicalException(String arg0) {
		super(arg0);
	}

	public CommandLogicalException(Throwable arg0) {
		super(arg0);
	}

}
