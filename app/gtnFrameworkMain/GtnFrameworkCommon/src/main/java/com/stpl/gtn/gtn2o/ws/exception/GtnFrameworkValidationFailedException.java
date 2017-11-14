package com.stpl.gtn.gtn2o.ws.exception;

public class GtnFrameworkValidationFailedException extends GtnFrameworkGeneralException {

	private final String componentId;

	public GtnFrameworkValidationFailedException(String message) {
		super(message);
		componentId = null;
	}

	public GtnFrameworkValidationFailedException(String message, String componentId) {
		super(message);
		this.componentId = componentId;
	}

	public GtnFrameworkValidationFailedException(String message, Exception exception) {
		super(message, exception);
		this.componentId = null;

	}

	public GtnFrameworkValidationFailedException(String message, Exception exception, String componentId) {
		super(message, exception);
		this.componentId = componentId;
	}

	public String getComponentId() {
		return componentId;
	}

	private static final long serialVersionUID = 1L;

}
