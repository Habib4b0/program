package com.stpl.gtn.gtn2o.ws.module.processscheduler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;



public class GtnWsCallEtlService {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCallEtlService.class);

	public GtnWsCallEtlService() {
		/**
		 * empty constructor
		 */
	}

	public void runShellScript(String scriptUrl) {
		LOGGER.info("Entering runShellScript with");
		try {
			URL url = new URL(scriptUrl);
			LOGGER.info("URL:host: "+url.getHost());
			LOGGER.info("URL:path: "+url.getPath());
			LOGGER.info("URL:port: "+url.getPort());
			LOGGER.info("URL:File: "+url.getFile());
			URLConnection urlConnection = url.openConnection();
			LOGGER.info("urlConnection: "+urlConnection);
			HttpURLConnection connection = null;
			if (urlConnection instanceof HttpURLConnection) {
				connection = (HttpURLConnection) urlConnection;
			} else {
				LOGGER.info("Please enter an HTTP URL.");
				return;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			LOGGER.info("Buffered String : "+in.toString());
			StringBuilder urlString = new StringBuilder();
			String current;

			while ((current = in.readLine()) != null) {
				urlString.append(current);
			}
			in.close();
		} catch (IOException e) {
			LOGGER.error("Exception while running script= "+ e);
		}
	}
	
}
