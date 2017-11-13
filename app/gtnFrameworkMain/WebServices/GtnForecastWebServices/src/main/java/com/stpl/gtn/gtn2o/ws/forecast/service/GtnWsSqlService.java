package com.stpl.gtn.gtn2o.ws.forecast.service;

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

import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.bean.sql.GtnWsSqlEntityElement;
import com.stpl.gtn.gtn2o.ws.bean.sql.GtnWsSqlRootElement;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Service
public class GtnWsSqlService {

	private final Map<String, String> queryMap = new HashMap<>();
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsSqlService.class);

	public GtnWsSqlService() throws GtnFrameworkGeneralException {
		logger.info("Entering GtnWsSqlService");
		init();

	}

	private void init() throws GtnFrameworkGeneralException {
		List<String> filePaths = loadAllFilePaths();
		for (String filePath : filePaths) {
			loadAllQueriesFromFile(filePath);
		}
	}

	private List<String> loadAllFilePaths() throws GtnFrameworkGeneralException {
		List<String> filePathsList = new ArrayList<>();
		Properties properties = new Properties();
		InputStream propertiesFileStream = GtnWsSqlService.class.getClassLoader()
				.getResourceAsStream("properties/forecasting/sql-resources.properties");
		try {
			properties.load(propertiesFileStream);
		} catch (IOException e) {
			throw new GtnFrameworkGeneralException("Error in loadAllFilePaths : " + e);
		}
		Iterator<?> iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			filePathsList.add(properties.get(iterator.next()).toString());
		}
		return filePathsList;
	}

	private void loadAllQueriesFromFile(String filePath) throws GtnFrameworkGeneralException {
		InputStream configInStream = GtnWsSqlService.class.getClassLoader().getResourceAsStream(filePath);
		getResources(configInStream);
	}

	private void getResources(InputStream configInStream) throws GtnFrameworkGeneralException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GtnWsSqlRootElement.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			GtnWsSqlRootElement que = (GtnWsSqlRootElement) jaxbUnmarshaller.unmarshal(configInStream);

			List<GtnWsSqlEntityElement> list = que.getSqlEntity();
			for (GtnWsSqlEntityElement ans : list) {
				queryMap.put(ans.getSqlID(), ans.getSqlQuery());
			}
		} catch (JAXBException e) {
			throw new GtnFrameworkGeneralException("Error in getResources : " + e);
		}
	}

	private Map<String, String> getqueryMap() {
		return queryMap;
	}

	public String getQuery(String sqlId) {
		return getqueryMap().get(sqlId);
	}

	public String getQueryWithReplacedValues(String sqlId, List<String> inputs) {
		StringBuilder sql = new StringBuilder(getqueryMap().get(sqlId));
		for (String input : inputs) {
			if (!(sql.indexOf("?") == -1)) {
				sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(input));
			}
		}
		return sql.toString();
	}
}
