package com.stpl.gtn.gtn2o.ws.db;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityPropertyBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.service.property.GtnWsSecurityPropertyReaderService;
import javax.naming.NamingException;

public class GtnDatabaseConnection {

	private static DataSource dataSource = null;
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnDatabaseConnection.class);

	static {
            try {
		if ((GtnWsSecurityPropertyReaderService.getProperyBean().isConnectDataSourceMode())
				&& (null != GtnWsSecurityPropertyReaderService.getProperyBean().getDataSourceName())) {
			
				Context initContext = new InitialContext();
				dataSource = (DataSource) initContext
						.lookup(GtnWsSecurityPropertyReaderService.getProperyBean().getDataSourceName());
			}} catch (Exception exception) {
				LOGGER.error("Exception in Initiating Datasorce ", exception);
		}
	}

	public Connection createConnection() {

		Connection connection = null;
		try {
			if (dataSource != null) {
				return dataSource.getConnection();
			}
			GtnWsSecurityPropertyBean gtnWsSecurityPropertBean = GtnWsSecurityPropertyReaderService.getProperyBean();
			connection = DriverManager.getConnection(gtnWsSecurityPropertBean.getDburl(),
					gtnWsSecurityPropertBean.getUsername(), gtnWsSecurityPropertBean.getPassword());
			return connection;

		} catch (Exception exception) {
			LOGGER.error("Exception in creating database connection - ", exception);
		}
		return connection;
	}

}
