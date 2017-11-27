package com.stpl.gtn.gtn2o.ws.bean.bcp;

public class GtnWsBcpServiceBean {
	public GtnWsBcpServiceBean() {
		super();
	}

	private String serverName;
	private String schemaName;
	private String userName;
	private String password;


	private String userId;
	private String sessionId;
	private String tableName;
	private String currentDateInString;
	private String finalFileName;
	private String folderName;
	
	private Object[] procedureInputs;
	private String tabName ;
	private String methodology;
	private String cumulativeFilePath;
	private Boolean salesFlag;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getCurrentDateInString() {
		return currentDateInString;
	}

	public void setCurrentDateInString(String currentDateInString) {
		this.currentDateInString = currentDateInString;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Object[] getProcedureInputs() {
		return procedureInputs.clone();
	}

	public void setProcedureInputs(Object[] procedureInputs) {
		this.procedureInputs = procedureInputs.clone();
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getMethodology() {
		return methodology;
	}

	public void setMethodology(String methodology) {
		this.methodology = methodology;
	}

	public String getCumulativeFilePath() {
		return cumulativeFilePath;
	}

	public void setCumulativeFilePath(String cumulativeFilePath) {
		this.cumulativeFilePath = cumulativeFilePath;
	}

	public Boolean getSalesFlag() {
		return salesFlag;
	}

	public void setSalesFlag(Boolean salesFlag) {
		this.salesFlag = salesFlag;
	}

}
