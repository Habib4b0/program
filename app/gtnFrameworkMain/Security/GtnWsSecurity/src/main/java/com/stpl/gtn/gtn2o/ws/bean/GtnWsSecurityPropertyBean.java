package com.stpl.gtn.gtn2o.ws.bean;

public class GtnWsSecurityPropertyBean {
	private String jdbcdrriver;
	private String dburl;
	private String username;
	private String password;
	private long expirationTime;
	private String filename;
	private String dataSourceName;
	private boolean connectDataSourceMode = false;

	public GtnWsSecurityPropertyBean() {
		super();
	}

	public String getJdbcdrriver() {
		return jdbcdrriver;
	}

	public void setJdbcdrriver(String jdbcdrriver) {
		this.jdbcdrriver = jdbcdrriver;
	}

	public String getDburl() {
		return dburl;
	}

	public void setDburl(String dburl) {
		this.dburl = dburl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public boolean isConnectDataSourceMode() {
		return connectDataSourceMode;
	}

	public void setConnectDataSourceMode(boolean connectDataSourceMode) {
		this.connectDataSourceMode = connectDataSourceMode;
	}

}
