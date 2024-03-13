package it.engineering.nc.app.exception;

public class ExaminationStillRunningException extends MyProjectException {
	private static final long serialVersionUID = -617629145266342586L;
	
	public ExaminationStillRunningException(String message) {
		super(message);
	}
}
