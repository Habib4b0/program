package com.stpl.gtn.gtn2o.ws.bean;

public class GtnWsSecurityToken {

	private String userId;

	private String sessionId;

	private String gtnToken;

	private String creationDate;
	

	public GtnWsSecurityToken() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getGtnToken() {
		return gtnToken;
	}

	public void setGtnToken(String gtnToken) {
		this.gtnToken = gtnToken;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
