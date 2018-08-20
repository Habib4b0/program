package com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.constant.ProcessSchedulerConstant;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.GtnWsCallEtlService;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.FtpProperties;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service()
@Scope(value = "singleton")
public class GtnWsProcessSchedularServiceUtil {
	
	public GtnWsProcessSchedularServiceUtil() {
		/*
		 * no need to implement
		 */
	}
	
	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessSchedularServiceUtil.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;
	
	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	public List<Object> getSearchInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();

		try {

			list.add(getSysSchemaCatalog());

			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				list.addAll(getSortedInputs());
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	public String getSysSchemaCatalog() throws GtnFrameworkGeneralException {
		String catalog = "";
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			catalog = connection.getCatalog();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new GtnFrameworkGeneralException("Exception in getSysSchemaCatalog", ex);
		}
		return catalog;
	}

	private List<Object> getSortedInputs() {
		List<Object> list = new ArrayList<>();
		list.add("WP.PROCESS_NAME" + " DESC");
		return list;
	}

	private static String getJbossHome() {
		return System.getProperty("jboss.home.dir");
	}

	private static String getPropertyPath() {
		return System.getProperty("com.stpl.gtnframework.base.path.property");
	}

	public static FtpProperties getFtpBundleValue() {
		logger.info("getFtpBundleValue===================>starts");
		FtpProperties ftpProperties = new FtpProperties();
		try {
			String jbossHome = getJbossHome();
			logger.info("jbossHome===================>" + jbossHome);
			if (!StringUtils.isBlank(jbossHome)) {
				java.util.Properties prop = getPropertyFile(getPropertyPath());
				logger.info("prop location: "+prop.getProperty(ProcessSchedulerConstant.ETL_CONFIGURATION_PROPERTY));
				java.util.Properties prop1 = getPropertyFile(
						jbossHome.concat("/../").concat(prop.getProperty(ProcessSchedulerConstant.ETL_CONFIGURATION_PROPERTY)));
				ftpProperties.setScripts(prop1.getProperty(ProcessSchedulerConstant.SCRIPTS));
				logger.info("setted the ftp pro scripts:" + prop1.getProperty(ProcessSchedulerConstant.SCRIPTS));
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.info("getFtpBundleValue===================>ends");
		return ftpProperties;
	}

	public static java.util.Properties getPropertyFile(String bpiPropLoc) {
		logger.info("getPropertyFile===================>starts");
		java.util.Properties prop = new java.util.Properties();
		FileInputStream fileIS = null;
		logger.info("pbiPropertyFile Location: " + bpiPropLoc);
		try {
			fileIS = GtnFileUtil.getFileInputStream(bpiPropLoc);
		} catch (FileNotFoundException exp) {
			logger.error("File not found"+exp);
		}

		try {
			prop.load(fileIS);
		} catch (IOException exp) {
			logger.error("Input output exception"+exp);
			
		}

		logger.info("getPropertyFile===================>ends");
		return prop;

	}

	public void runJob(FtpProperties ftpProperties, String scriptName) {
		try {
			logger.info("Script Name= {}" + scriptName);
			String jbossHome = getJbossHome();
			logger.info("jboss Home= {}" + jbossHome);
			if (!StringUtils.isBlank(jbossHome)) {
				java.util.Properties prop = getPropertyFile(getPropertyPath());
				
				java.util.Properties prop1 = getPropertyFile(
						jbossHome.concat("/../").concat(prop.getProperty(ProcessSchedulerConstant.ETL_CONFIGURATION_PROPERTY)));
				String etlInterfaceUri = buildUrl(scriptName, prop1);
				ftpProperties.setScripts(prop1.getProperty("scripts"));
				runShellScript(etlInterfaceUri);
			}
			logger.info("runShellScript===================>ends1");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.info("runJob ends");
	}

	public String buildUrl(String scriptName, Properties prop) {
		String interfaceUri = getInterFaceUri(scriptName);
		String portNo = prop.getProperty("ETL_PORT_NO");
		logger.info("http://localhost:" + portNo + "/" + interfaceUri);
		return "http://localhost:" + portNo + "/" + interfaceUri;
	}

	private String getInterFaceUri(String scriptName) {
		String jbossHome = getJbossHome();
		java.util.Properties interfaceUriProperties = getPropertyFile(getPropertyPath());
		java.util.Properties interfacename = getPropertyFile(
				jbossHome.concat("/../").concat(interfaceUriProperties.getProperty("Interfaceuri.properties")));
		return interfacename.getProperty(scriptName).trim();
	}

	public void runShellScript(String scriptUrl) {
		logger.info("Entering runShellScript ");
		GtnWsCallEtlService etlService=new GtnWsCallEtlService();
		etlService.runShellScript(scriptUrl);
	}

	public void deleteUnsavedProjections(String deleteQuery) throws GtnFrameworkGeneralException {
		List<Object> inputList = new ArrayList<>();
		inputList.add(getSysSchemaCatalog());
		try {
			String query = gtnWsSqlService.getQuery(inputList, deleteQuery);
			@SuppressWarnings("unchecked")
			List<Object[]> result = executeQuery(query);
			logger.info("records deleted = "+result.size());
			
		} catch (Exception ex) {
			logger.info("Intial Session Delete QUERY ERROR=  " + deleteQuery);
			logger.error(ex.getMessage());
		}
		
	}
}
