package com.stpl.gtn.gtn2o.ws.service.property;

import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityPropertyBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnWsSecurityPropertyReaderService {
	private static volatile GtnWsSecurityPropertyBean gtnWsSecurityPropertyBean;
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsSecurityPropertyReaderService.class);

	private GtnWsSecurityPropertyReaderService() {
		super();	
	}

	public static GtnWsSecurityPropertyBean getProperyBean() {

		if (gtnWsSecurityPropertyBean == null) {
			gtnWsSecurityPropertyBean = new GtnWsSecurityPropertyBean();
			try {
				gtnWsSecurityPropertyBean
						.setJdbcdrriver(GtnFrameworkPropertyManager.getProperty("gtn.security.jdbc_driver"));
				gtnWsSecurityPropertyBean.setDburl(GtnFrameworkPropertyManager.getProperty("gtn.security.db_url"));
				gtnWsSecurityPropertyBean.setUsername(GtnFrameworkPropertyManager.getProperty("gtn.security.username"));
				gtnWsSecurityPropertyBean.setPassword(GtnFrameworkPropertyManager.getProperty("gtn.security.password"));
				gtnWsSecurityPropertyBean.setExpirationTime(
						Long.parseLong(GtnFrameworkPropertyManager.getProperty("gtn.security.expirationTime.ms")));
				gtnWsSecurityPropertyBean.setFilename(GtnFrameworkPropertyManager.getProperty("gtn.security.filename"));
				gtnWsSecurityPropertyBean
						.setDataSourceName(GtnFrameworkPropertyManager.getProperty("gtn.security.datasourceName"));
				if ("true".equalsIgnoreCase(
						GtnFrameworkPropertyManager.getProperty("gtn.security.connectionMode.datasource"))) {
					gtnWsSecurityPropertyBean.setConnectDataSourceMode(true);
				}
			} catch (Exception e) {
				LOGGER.error("Exception in initialaising security property bean " + e);

			}
		}
		return gtnWsSecurityPropertyBean;
	}
}