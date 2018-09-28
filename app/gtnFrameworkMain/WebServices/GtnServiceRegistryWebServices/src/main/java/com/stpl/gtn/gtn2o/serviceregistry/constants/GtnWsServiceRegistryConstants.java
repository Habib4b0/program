package com.stpl.gtn.gtn2o.serviceregistry.constants;

public class GtnWsServiceRegistryConstants {

	private GtnWsServiceRegistryConstants() {

	}

	public static final String INSERT_QUERY = "DELETE FROM SERVICE_REGISTRY WHERE REGISTERED_WEB_CONTEXT = ? ; INSERT INTO SERVICE_REGISTRY (WEB_SERVICE_END_POINT_URL,REGISTERED_WEB_CONTEXT) VALUES (?,?)";
	public static final String SELECT_QUERY = "SELECT * FROM SERVICE_REGISTRY WHERE REGISTERED_WEB_CONTEXT = ?";
	public static final String TIME="HH:mm:ss";
	public static final String PORT="http://localhost:8090";
	public static final String PERIOD_CONF="/GtnWsPeriodConfigurationWebService";
	public static final String REGISTER_WEBSERVICE="Webservice to Register:";
	public static final String USER_ID="20156";
	public static final String SESSION_ID="20156";
	public static final String PERIOD_CONF_URL="/gtnPeriodConfigurationController/loadDate";
	
	
}
