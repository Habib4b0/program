/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.util.xmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jboss.logging.Logger;

import com.stpl.gtn.gtn2o.ws.bean.sql.GtnWsSqlEntityElement;
import com.stpl.gtn.gtn2o.ws.bean.sql.GtnWsSqlRootElement;

/**
 *
 * @author STPL
 */
public class SQLUtility {

	private final Map<String, String> queryMap = new HashMap<>();
	private static volatile SQLUtility sqlUtil = null;
	private static final Logger LOGGER = Logger.getLogger(SQLUtility.class);

	private SQLUtility() {
		try {
			init();
		} catch (IOException | JAXBException e) {
			LOGGER.error(e.getMessage());
		}
	}

	private void init() throws IOException, JAXBException {
		List<String> filePaths = loadAllFilePaths();
		for (String filePath : filePaths) {
			loadAllQueriesFromFile(filePath);
		}
	}

	private List<String> loadAllFilePaths() throws IOException {
		List<String> filePathsList = new ArrayList<>();
		Properties properties = new Properties();
		InputStream propertiesFileStream = SQLUtility.class.getClassLoader()
				.getResourceAsStream("/properties/sql-resources.properties");
		properties.load(propertiesFileStream);
		@SuppressWarnings("rawtypes")
		Iterator iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			filePathsList.add(properties.get(iterator.next()).toString());
		}
		return filePathsList;
	}

	private void loadAllQueriesFromFile(String filePath) throws JAXBException {
		InputStream configInStream = SQLUtility.class.getClassLoader().getResourceAsStream(filePath);
		getResources(configInStream);
	}

	private static SQLUtility getContext() {
		if (sqlUtil == null) {
			sqlUtil = new SQLUtility();
		}
		return sqlUtil;
	}

	private void getResources(InputStream configInStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(GtnWsSqlRootElement.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GtnWsSqlRootElement que = (GtnWsSqlRootElement) jaxbUnmarshaller.unmarshal(configInStream);
		List<GtnWsSqlEntityElement> list = que.getSqlEntity();
		for (GtnWsSqlEntityElement ans : list) {
			queryMap.put(ans.getSqlID(), ans.getSqlQuery());
		}
	}

	private Map<String, String> getQueryMap() {
		return queryMap;
	}

	public static String getQuery(String sqlId) {
		return SQLUtility.getContext().getQueryMap().get(sqlId);
	}

	public static String getQueryWithReplacedValues(String sqlId, List<String> inputs) {
		StringBuilder sql = new StringBuilder(SQLUtility.getContext().getQueryMap().get(sqlId));
		for (String input : inputs) {
			if (sql.indexOf("?") != -1) {
				sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(input));
			}
		}
		return sql.toString();
	}

}
