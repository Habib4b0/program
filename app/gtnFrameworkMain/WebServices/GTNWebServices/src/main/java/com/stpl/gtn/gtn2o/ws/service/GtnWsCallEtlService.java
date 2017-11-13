package com.stpl.gtn.gtn2o.ws.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Service()
@Scope(value = "singleton")
public class GtnWsCallEtlService {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCallEtlService.class);

	public GtnWsCallEtlService() {
		/**
		 * empty constructor
		 */
	}

	public void runShellScript(String scriptUrl) {
		LOGGER.info("Entering runShellScript with " + scriptUrl);
		try {
			URL url = new URL(scriptUrl);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection connection = null;
			if (urlConnection instanceof HttpURLConnection) {
				connection = (HttpURLConnection) urlConnection;
			} else {
				LOGGER.info("Please enter an HTTP URL.");
				return;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder urlString = new StringBuilder("");
			String current;

			while ((current = in.readLine()) != null) {
				urlString.append(current);
			}
			in.close();
			LOGGER.info(urlString.toString());
		} catch (Exception e) {
			LOGGER.error("Exception while running script.", e);
		}
	}

	public String buildUrl(String tableName) {
		String interfaceUri = getInterFaceUri(tableName);
		String portNo = getPortNo();
		return "http://localhost:" + portNo + "/" + interfaceUri;
	}

	private String getInterFaceUri(String tableName) {
		LOGGER.info(System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH)
				+ GtnFrameworkPropertyManager.getProperty("gtn.etl.interfaceUriConfigPath"));
		String interfaceName = tableName.toUpperCase(Locale.ENGLISH) + "_INTERFACE";
		return getProperty(System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH)
				+ GtnFrameworkPropertyManager.getProperty("gtn.etl.interfaceUriConfigPath"), interfaceName);
	}

	public String getPortNo() {

		LOGGER.info(System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH)
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservice.ftpconfigurationpath"));
		return getProperty(
				System.getProperty(GtnFrameworkCommonStringConstants.GTN_BASE_PATH)
						+ GtnFrameworkPropertyManager.getProperty("gtn.webservice.ftpconfigurationpath"),
				"ETL_PORT_NO");
	}

	public String getProperty(String path, String key) {
		String value = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		try {
			value = GtnFrameworkPropertyManager.getProperty(path, key);
		} catch (IOException ex) {
			LOGGER.error("error--", ex);
		}
		return value;
	}
}
