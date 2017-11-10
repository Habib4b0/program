package com.stpl.gtn.gtn2o.ui.framework.engine.session;

import java.util.HashMap;
import java.util.Map;

public class GtnUIFrameworkSessionBean {

	private String userId=null;

	private String userName=null;

	private String sessionId=null;

	private Map<String, Object> gtnSessionMap = new HashMap<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, Object> getGtnSessionMap() {
		return gtnSessionMap;
	}

	public void addSessionProperty(String key, Object value) {
		gtnSessionMap.put(key, value);
	}

	public Object getSessionProperty(String key) {
		return gtnSessionMap.get(key);
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
