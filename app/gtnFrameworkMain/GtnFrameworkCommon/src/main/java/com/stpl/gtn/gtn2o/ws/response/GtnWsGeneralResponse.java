package com.stpl.gtn.gtn2o.ws.response;

public class GtnWsGeneralResponse implements GtnWSResponseData {

	public GtnWsGeneralResponse() {
		super();
	}

	private boolean sucess = true;
	private Exception gtnGeneralException;
	private String userName;

	public boolean isSucess() {
		return sucess;
	}

	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public Exception getGtnGeneralException() {
		return gtnGeneralException;
	}

	public void setGtnGeneralException(Exception gtnGeneralException) {
		this.gtnGeneralException = gtnGeneralException;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
