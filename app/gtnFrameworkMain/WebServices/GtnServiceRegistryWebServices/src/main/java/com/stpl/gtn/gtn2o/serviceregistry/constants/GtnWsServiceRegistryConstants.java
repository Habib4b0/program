package com.stpl.gtn.gtn2o.serviceregistry.constants;

public class GtnWsServiceRegistryConstants {
	
	public static final String INSERT_QUERY = "DELETE FROM SERVICE_REGISTRY WHERE REGISTERED_WEB_CONTEXT = ? ; INSERT INTO SERVICE_REGISTRY (HOST_NAME,PORT,REGISTERED_WEB_CONTEXT) VALUES (?,?,?)";
	public static final String SELECT_QUERY = "SELECT * FROM SERVICE_REGISTRY WHERE REGISTERED_WEB_CONTEXT = ?";
}
