package it.engineering.nc.app.exception;

public class BadCredentialsException extends MyProjectException {

	private static final long serialVersionUID = 5136874209228872212L;

	public BadCredentialsException(String message) {
		super(message);
	}

}
