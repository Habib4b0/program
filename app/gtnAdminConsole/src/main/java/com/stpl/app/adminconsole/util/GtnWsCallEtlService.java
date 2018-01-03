package com.stpl.app.adminconsole.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.jboss.logging.Logger;



public class GtnWsCallEtlService {
	private static final Logger LOGGER = Logger.getLogger(GtnWsCallEtlService.class);

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
	
}

