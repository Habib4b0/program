package com.stpl.gtn.gtn2o.ws.serviceregistry.bean;

public class GtnWsServiceRegistryBean {

	private boolean authorizaionService = false;

	private boolean registeredService = false;

	private String hostName;

	private String port;

	private String registeredWebContext;

	private String url;

	private String moduleName;
	private String webserviceEndPointUrl;

	public GtnWsServiceRegistryBean() {
		super();
	}

	public boolean isAuthorizaionService() {
		return authorizaionService;
	}

	public void setAuthorizaionService(boolean authorizaionService) {
		this.authorizaionService = authorizaionService;
	}

	public boolean isRegisteredService() {
		return registeredService;
	}

	public void setRegisteredService(boolean registeredService) {
		this.registeredService = registeredService;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getRegisteredWebContext() {
		return registeredWebContext;
	}

	public void setRegisteredWebContext(String registeredWebContext) {
		this.registeredWebContext = registeredWebContext;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getWebserviceEndPointUrl() {
		return webserviceEndPointUrl;
	}

	public void setWebserviceEndPointUrl(String webserviceEndPointUrl) {
		this.webserviceEndPointUrl = webserviceEndPointUrl;
	}

}
