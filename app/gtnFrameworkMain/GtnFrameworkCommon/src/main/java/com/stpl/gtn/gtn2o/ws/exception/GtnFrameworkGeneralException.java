
package com.stpl.gtn.gtn2o.ws.exception;

public class GtnFrameworkGeneralException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String message;

	private final Exception exception;

	public GtnFrameworkGeneralException(String message) {
		super(message);
		this.message = message;
		this.exception = null;
	}

	public GtnFrameworkGeneralException(Exception exception) {
		super(exception);
		this.exception = exception;
		this.message = "";
	}

	public GtnFrameworkGeneralException(String message, Exception exception) {
		super(message, exception);
		this.message = message;
		this.exception = exception;
	}

	public String getErrorMessage() {
		if (exception == null) {
			return message;
		}
		return message + exception.getMessage();
	}

	public Exception getException() {
		return exception;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
