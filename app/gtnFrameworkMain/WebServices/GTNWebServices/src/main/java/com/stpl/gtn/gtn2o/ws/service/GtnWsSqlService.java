package com.stpl.gtn.gtn2o.ws.service;

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

import com.stpl.gtn.gtn2o.ws.bean.sql.GtnWsSqlEntityElement;
import com.stpl.gtn.gtn2o.ws.bean.sql.GtnWsSqlRootElement;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnWsSqlService {

	private final Map<String, String> queryMap = new HashMap<>();
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsSqlService.class);

	private GtnWsSqlService() {
		try {
			init();
		} catch (IOException | JAXBException e) {
			logger.error(e.getMessage());
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
		InputStream propertiesFileStream = getInputStream("/properties/sql-resources.properties");
		properties.load(propertiesFileStream);
		Iterator<?> iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			filePathsList.add(properties.get(iterator.next()).toString());
		}
		return filePathsList;
	}

	private void loadAllQueriesFromFile(String filePath) throws JAXBException, IOException {
		InputStream configInStream = getInputStream(filePath);
		getResources(configInStream);
	}

	private void getResources(InputStream configInStream) throws JAXBException, IOException {
		JAXBContext jaxbContext = JAXBContext.newInstance(GtnWsSqlRootElement.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GtnWsSqlRootElement que = (GtnWsSqlRootElement) jaxbUnmarshaller.unmarshal(configInStream);
		List<GtnWsSqlEntityElement> list = que.getSqlEntity();
		for (GtnWsSqlEntityElement ans : list) {
			queryMap.put(ans.getSqlID(), ans.getSqlQuery());
		}
	}

	private Map<String, String> getqueryMap() {
		return queryMap;
	}

	public String getQuery(String sqlId) {
		return getqueryMap().get(sqlId);
	}

	private InputStream getInputStream(String filePath) {
		InputStream inputStream = GtnWsSqlService.class.getResourceAsStream(filePath);
		if (inputStream == null) {
			inputStream = GtnWsSqlService.class.getClassLoader().getResourceAsStream(filePath);
		}
		if (inputStream == null) {
			inputStream = getClass().getResourceAsStream(filePath);
		}
		if (inputStream == null) {
			inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
		}
		return inputStream;
	}

	@SuppressWarnings("rawtypes")
	public String getQuery(List input, String queryName) {
		StringBuilder sql = new StringBuilder();
		try {
			sql = new StringBuilder(getQuery(queryName));
			if (input != null) {
				for (Object temp : input) {
					if (sql.indexOf("?") != -1) {
						sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
					}
				}
			}

		} catch (Exception ex) {
			logger.error("Exception in getQuery", ex);
		}
		return sql.toString();
	}

	@SuppressWarnings("rawtypes")
	public String getReplacedQuery(List input, String query) {
		StringBuilder sql = new StringBuilder();
		try {
			sql = new StringBuilder(query);
			if (input != null) {
				for (Object temp : input) {
					if (sql.indexOf("?") != -1) {
						sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
					}
				}
			}

		} catch (Exception ex) {
			logger.error("Exception in getQuery", ex);
		}
		return sql.toString();
	}
}
