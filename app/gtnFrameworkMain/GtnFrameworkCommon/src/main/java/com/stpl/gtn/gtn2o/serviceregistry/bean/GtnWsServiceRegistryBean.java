package com.stpl.gtn.gtn2o.serviceregistry.bean;

public class GtnWsServiceRegistryBean {

	private boolean authorizaionService = false;
	
	private boolean registeredService = false;

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
	
}
