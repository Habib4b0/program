package com.stpl.gtn.gtn2o.ws.constant;

public final class GtnWsQueryConstant {

	public static final String SELECT_QUERY = "SELECT * FROM GTNWS_AUTH_TOKEN  WHERE  SESSION_ID= ? AND USER_NAME = ?";
	public static final String INSERT_QUERY = "INSERT INTO GTNWS_AUTH_TOKEN(USER_NAME, SESSION_ID, OAUTH_TOKEN)  values(?,?,?)";
	public static final String DELETE_QUERY = "DELETE FROM  GTNWS_AUTH_TOKEN WHERE  SESSION_ID= ? AND USER_NAME = ? ";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private GtnWsQueryConstant() {
		super();
	}

}
