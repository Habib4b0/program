package com.stpl.gtn.gtn2o.ws.service.sql;

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

	public GtnWsSqlService() {
		try {
			init();
		} catch (IOException | JAXBException e) {
			logger.error(e.getMessage());
		}
	}

	private void init() throws IOException, JAXBException {
		List<String> bpmWsFilePaths = loadAllFilePaths();
		for (String filePath : bpmWsFilePaths) {
			loadAllQueriesFromFile(filePath);
		}
	}

	private List<String> loadAllFilePaths() throws IOException {
		List<String> bpmWsFilePathsList = new ArrayList<>();
		Properties properties = new Properties();
		InputStream propertiesFileStream = getInputStream("/properties/sql-resources.properties");
		properties.load(propertiesFileStream);
		Iterator<?> iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			bpmWsFilePathsList.add(properties.get(iterator.next()).toString());
		}
		return bpmWsFilePathsList;
	}

	private void loadAllQueriesFromFile(String bpmWsFilePath) throws JAXBException, IOException {
		InputStream configInStream = getInputStream(bpmWsFilePath);
		getResources(configInStream);
	}

	private void getResources(InputStream configInStream) throws JAXBException, IOException {
		JAXBContext jaxbContext = JAXBContext.newInstance(GtnWsSqlRootElement.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GtnWsSqlRootElement que = (GtnWsSqlRootElement) jaxbUnmarshaller.unmarshal(configInStream);
		List<GtnWsSqlEntityElement> bpmWsSqlElementList = que.getSqlEntity();
		for (GtnWsSqlEntityElement ans : bpmWsSqlElementList) {
			queryMap.put(ans.getSqlID(), ans.getSqlQuery());
		}
	}

	private Map<String, String> getqueryMap() {
		return queryMap;
	}

	public String getQuery(String bpmWsSqlId) {
		return getqueryMap().get(bpmWsSqlId);
	}

	private InputStream getInputStream(String filePath) {
		InputStream bpmWsinputStream = GtnWsSqlService.class.getResourceAsStream(filePath);
		if (bpmWsinputStream == null) {
			bpmWsinputStream = GtnWsSqlService.class.getClassLoader().getResourceAsStream(filePath);
		}
		if (bpmWsinputStream == null) {
			bpmWsinputStream = getClass().getResourceAsStream(filePath);
		}
		if (bpmWsinputStream == null) {
			bpmWsinputStream = getClass().getClassLoader().getResourceAsStream(filePath);
		}
		return bpmWsinputStream;
	}
}
