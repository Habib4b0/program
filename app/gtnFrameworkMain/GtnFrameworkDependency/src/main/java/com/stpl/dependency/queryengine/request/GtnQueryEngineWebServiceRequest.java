package com.stpl.dependency.queryengine.request;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;

public class GtnQueryEngineWebServiceRequest {

	private GtnFrameworkQueryExecutorBean queryExecutorBean;
	private String userId;
	private String sessionId;
	private String token;

	public GtnFrameworkQueryExecutorBean getQueryExecutorBean() {
		return queryExecutorBean;
	}

	public void setQueryExecutorBean(GtnFrameworkQueryExecutorBean queryExecutorBean) {
		this.queryExecutorBean = queryExecutorBean;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
