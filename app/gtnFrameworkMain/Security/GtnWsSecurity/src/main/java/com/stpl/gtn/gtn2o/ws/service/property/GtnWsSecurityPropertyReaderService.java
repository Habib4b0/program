package com.stpl.gtn.gtn2o.ws.service.property;

import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityPropertyBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnWsSecurityPropertyReaderService {
        private static final GtnWsSecurityPropertyBean PROPERTY_BEAN;
        private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsSecurityPropertyReaderService.class);

        private GtnWsSecurityPropertyReaderService() {
                super();
        }

        static {
                PROPERTY_BEAN = new GtnWsSecurityPropertyBean();
                try {
                        PROPERTY_BEAN.setJdbcdrriver(
                                        GtnFrameworkPropertyManager.getProperty("gtn.security.jdbc_driver"));
                        PROPERTY_BEAN.setDburl(GtnFrameworkPropertyManager.getProperty("gtn.security.db_url"));
                        PROPERTY_BEAN.setUsername(GtnFrameworkPropertyManager.getProperty("gtn.security.username"));
                        PROPERTY_BEAN.setPassword(GtnFrameworkPropertyManager.getProperty("gtn.security.password"));
                        PROPERTY_BEAN.setExpirationTime(Long.parseLong(
                                        GtnFrameworkPropertyManager.getProperty("gtn.security.expirationTime.ms")));
                        PROPERTY_BEAN.setFilename(GtnFrameworkPropertyManager.getProperty("gtn.security.filename"));
                        PROPERTY_BEAN.setDataSourceName(
                                        GtnFrameworkPropertyManager.getProperty("gtn.security.datasourceName"));
                        if ("true".equalsIgnoreCase(GtnFrameworkPropertyManager
                                        .getProperty("gtn.security.connectionMode.datasource"))) {
                                PROPERTY_BEAN.setConnectDataSourceMode(true);
                        }
                } catch (Exception e) {
                        LOGGER.error("Exception in initialaising security property bean " + e);

                }
        }

        public static GtnWsSecurityPropertyBean getProperyBean() {
                return PROPERTY_BEAN;
        }
}